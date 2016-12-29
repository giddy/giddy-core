package com.riders.giddy.api.v1.controllers;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.riders.giddy.api.v1.services.s3.S3Wrapper;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api(name = "giddy-collector", description = "This is the core collector API of Giddy")
@ApiVersion(since = "v1")

@RestController
@RequestMapping("/api/v1/collector")
public class UploadController {

	@Autowired
	private S3Wrapper s3Wrapper;

	@ApiMethod(description = "Upload gpx files")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public List<PutObjectResult> upload(
			@ApiQueryParam(description = "This is an array of multipart files")
			@RequestParam("file") MultipartFile[] multipartFiles) {
		return s3Wrapper.upload(multipartFiles);
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
}
