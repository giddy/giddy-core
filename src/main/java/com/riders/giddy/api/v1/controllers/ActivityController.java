package com.riders.giddy.api.v1.controllers;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.riders.giddy.api.v1.models.score.embeddable.GiddyScore;
import com.riders.giddy.api.v1.services.core.dispatcher.RouteDispatcher;
import com.riders.giddy.api.v1.services.core.utils.ValidationError;
import com.riders.giddy.api.v1.services.core.utils.ValidationErrorBuilder;
import com.riders.giddy.api.v1.services.s3.S3Wrapper;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    @PostMapping(value = "/activities")
    public
    @ResponseBody
    ResponseEntity saveActivity(
            @RequestParam("file") MultipartFile gpxRoute, @RequestParam Map<String, Float> params, ModelMap model) throws IOException {

        boolean isCompleted = routeDispatcher.updateRouteDescription(gpxRoute, new GiddyScore(params));

        return new ResponseEntity(isCompleted ? OK : INTERNAL_SERVER_ERROR);
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
