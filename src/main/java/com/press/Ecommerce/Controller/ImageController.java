package com.press.Ecommerce.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.press.Ecommerce.Response.APIResponse;
import com.press.Ecommerce.dto.ImageDto;
import com.press.Ecommerce.exception.ImageNotFoundException;
import com.press.Ecommerce.exception.ResourceNotFoundException;
import com.press.Ecommerce.modal.Image;
import com.press.Ecommerce.service.image.ImageService;

@RestController
@RequestMapping("${api.prefix}/image")
public class ImageController {

	
	
	@Autowired
	private ImageService imageService;
	
	@PostMapping("/upload")
	public ResponseEntity<APIResponse> saveImages(
												@RequestParam List<MultipartFile> files,
												@RequestParam Long productId)
	{
		
		try {
			
			List<ImageDto> saveImage = imageService.saveImage(files, productId);
			return ResponseEntity.ok(new APIResponse("Upload Succesfully!!", saveImage));
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse("upload failed!!!", e.getMessage()));
		}
		
	}
	
//	@GetMapping("/image/download/{imageId}")
//	public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException{
//		
//		Image imageById = imageService.getImageById(imageId);
//		
//		ByteArrayResource resource = new ByteArrayResource(imageById.getImage().getBytes(1, (int)imageById.getImage().length()));
//		
//		return ResponseEntity.ok().contentType(MediaType.parseMediaType(imageById.getFileType()))
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +imageById.getFileName() + "\"").body(resource);
//	}
	
	// Add a method in your controller for downloading images
//	@GetMapping("/image/download/{imageId}")
//	public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws IOException {
//	    Image image = imageService.getImageById(imageId);
//	    ByteArrayResource resource = new ByteArrayResource(((MultipartFile) image.getImage()).getBytes());
//
//	    return ResponseEntity.ok()
//	            .contentType(MediaType.parseMediaType(image.getFileType()))
//	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
//	            .body(resource);
//	}

//	// Include image URL in the response
//	public class ImageDto {
//	    private Long id;
//	    private String fileName;
//	    private String fileType;
//	    private String downloadUrl; // URL for image download
//	}
	
	
	@GetMapping("/image/download/{id}")
	public ResponseEntity<byte[]> downloadImage(@PathVariable Long id) {
        Image image = imageService.getImageById(id);

        // Set the content type based on the file type of the image
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
                .body(image.getImage());
    }
	
	 @PutMapping("/image/{imageId}/upadte")
	 public ResponseEntity<APIResponse> updateImage(@PathVariable Long imageId, @RequestParam MultipartFile file){
		 
		 
		 try {
			Image imageById = imageService.getImageById(imageId);
			 
			 if(imageById!= null) {
				 imageService.updateImage(file, imageId);
				 return ResponseEntity.ok(new APIResponse("Update successfully!!!", " "));
			 }
		} catch (ImageNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(), " "));
		}
		 
		 
		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse("Upload Failed!!!", " ")); 
	 }
	 
	 @DeleteMapping("/image/{imageId}/delete")
	 public ResponseEntity<APIResponse> deleteImage(@PathVariable Long imageId){
		 
		 
		 try {
			Image imageById = imageService.getImageById(imageId);
			 
			 if(imageById!= null) {
				 imageService.deleteImageById(imageId);
				 return ResponseEntity.ok(new APIResponse("Delete successfully!!!", " "));
			 }
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(), " "));
		}
		 
		 
		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse("delete Failed!!!", " ")); 
	 }
	
	
}
