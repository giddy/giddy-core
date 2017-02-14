package com.riders.giddy.api.v1.controllers;

import com.riders.giddy.api.v1.services.s3.S3Wrapper;
import com.riders.giddy.api.v1.utils.ValidationError;
import com.riders.giddy.api.v1.utils.ValidationErrorBuilder;
import com.riders.giddy.commons.persistence.store.entities.GiddyScoreDescriptor;
import com.riders.giddy.dispatcher.RouteDispatcher;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Api(name = "giddy-collector", description = "This is the core collector API of Giddy")
@ApiVersion(since = "v1")

@RestController
@RequestMapping("/api/v1/collector")
public class ActivityController {

    @Autowired
    private S3Wrapper s3Wrapper;

    @Autowired
    private RouteDispatcher routeDispatcher;

    @ApiMethod(description = "Save activity")
    @RequestMapping(value = "/activities", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity saveActivity(
            @RequestParam("file") MultipartFile gpxRoute, @Valid @ModelAttribute GiddyScoreDescriptor desc) throws IOException {


        boolean isCompleted = routeDispatcher.updateRouteDescription(multipartToFile(gpxRoute), new GiddyScoreDescriptor());

        return new ResponseEntity(isCompleted ? OK : INTERNAL_SERVER_ERROR);
    }

    File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }

	/*@ApiMethod(description = "Upload gpx file")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
	public List<PutObjectResult> upload(
			@ApiQueryParam(name = "activityId", description = "This is the activity id")
			@RequestParam(name = "activityId")
			Integer activityId,
			@ApiQueryParam(name = "file", description = "This is the activity gpx file")
			@NotNull
			@RequestParam(name = "file")
			MultipartFile file) {
		return s3Wrapper.upload(file, activityId);
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
	}*/

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(MethodArgumentNotValidException exception) {
        return createValidationError(exception);
    }

    private ValidationError createValidationError(MethodArgumentNotValidException exception) {
        return ValidationErrorBuilder.fromBindingErrors(exception.getBindingResult());
    }
}
