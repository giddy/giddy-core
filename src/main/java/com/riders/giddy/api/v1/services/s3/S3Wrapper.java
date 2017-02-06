package com.riders.giddy.api.v1.services.s3;

import org.springframework.stereotype.Service;

@Service
public class S3Wrapper {
/*
    @Autowired
	private AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${cloud.aws.s3.default_path}")
	private String defaultPath;

	private PutObjectResult upload(String filePath, String uploadKey) throws FileNotFoundException {
		return upload(new FileInputStream(filePath), uploadKey);
	}

	private PutObjectResult upload(InputStream inputStream, String uploadKey) {
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, uploadKey, inputStream, new ObjectMetadata());

		putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);

		PutObjectResult putObjectResult = amazonS3Client.putObject(putObjectRequest);

		IOUtils.closeQuietly(inputStream);

		return putObjectResult;
	}

	public List<PutObjectResult> upload(MultipartFile multipartFile, Integer activityId) {
		List<PutObjectResult> putObjectResults = new ArrayList<>();

		if(!StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
			try {
				putObjectResults.add(upload(multipartFile.getInputStream(),
						this.defaultPath
								.concat(activityId.toString() + '-')
								.concat(multipartFile.getOriginalFilename())));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return putObjectResults;
	}

	public ResponseEntity<byte[]> download(String key) throws IOException {
		GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, key);

		S3Object s3Object = amazonS3Client.getObject(getObjectRequest);

		S3ObjectInputStream objectInputStream = s3Object.getObjectContent();

		byte[] bytes = IOUtils.toByteArray(objectInputStream);

		String fileName = URLEncoder.encode(key, "UTF-8").replaceAll("\\+", "%20");

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		httpHeaders.setContentLength(bytes.length);
		httpHeaders.setContentDispositionFormData("attachment", fileName);

		return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
	}

	public List<S3ObjectSummary> list() {
		ObjectListing objectListing = amazonS3Client.listObjects(new ListObjectsRequest().withBucketName(bucket));

		List<S3ObjectSummary> s3ObjectSummaries = objectListing.getObjectSummaries();

		return s3ObjectSummaries;
	}*/
}
