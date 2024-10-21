package com.press.Ecommerce.service.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.press.Ecommerce.dto.ImageDto;
import com.press.Ecommerce.modal.Image;

public interface ImageService {
	
	Image getImageById(Long id);
	void deleteImageById(Long id);
	List<ImageDto> saveImage(List<MultipartFile> files, Long productId);
	void updateImage(MultipartFile file, Long imageId);

}
