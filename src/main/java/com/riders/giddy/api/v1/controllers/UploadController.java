package com.riders.giddy.api.v1.controllers;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.riders.giddy.api.v1.models.GiddyActivity;
import com.riders.giddy.api.v1.models.dao.GiddyActivityRepository;
import com.riders.giddy.api.v1.services.s3.S3Wrapper;
import com.riders.giddy.api.v1.utils.ValidationError;
import com.riders.giddy.api.v1.utils.ValidationErrorBuilder;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Api(name = "giddy-collector", description = "This is the core collector API of Giddy")
@ApiVersion(since = "v1")

@RestController
@RequestMapping("/api/v1/collector")
public class UploadController {

	@Autowired
	private S3Wrapper s3Wrapper;

	@Autowired
	private GiddyActivityRepository giddyActivityRepository;

	@ApiMethod(description = "Upload gpx files and create activity")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public List<PutObjectResult> upload(
			@ApiQueryParam(description = "This is the activity object")
			@Valid
			@RequestBody
			GiddyActivity giddyActivity) {
		giddyActivityRepository.save(giddyActivity); // TODO Refactor into GiddyActivityService
		return s3Wrapper.upload(giddyActivity.getFile());
	}

	@ApiMethod(description = "Download gpx files")
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(
			@ApiQueryParam(description = "This is the filename")
			@RequestParam String key) throws IOException {
		return s3Wrapper.download(key);
	}

	@ApiMethod(description = "List gpx files")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<S3ObjectSummary> list() throws IOException {
		return s3Wrapper.list();
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ValidationError handleException(MethodArgumentNotValidException exception) {
		return createValidationError(exception);
	}

	private ValidationError createValidationError(MethodArgumentNotValidException exception) {
		return ValidationErrorBuilder.fromBindingErrors(exception.getBindingResult());
	}
}
