package com.press.Ecommerce.service.image;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.press.Ecommerce.dto.ImageDto;
import com.press.Ecommerce.exception.ImageNotFoundException;
import com.press.Ecommerce.modal.Image;
import com.press.Ecommerce.modal.Product;
import com.press.Ecommerce.repository.ImageRepository;

import com.press.Ecommerce.service.product.ProductService;




@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private ProductService productService; 

	@Override
	public Image getImageById(Long id) {
		// TODO Auto-generated method stub
		return imageRepository.findById(id).orElseThrow(()-> new ImageNotFoundException("Image Not Found!!!"+id));
	}

	@Override
	public void deleteImageById(Long id) {
		imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, ()->{
			throw new ImageNotFoundException("Image Not Found!!!"+id); 
		});
		
	}

	@Override
	public List<ImageDto> saveImage(List<MultipartFile> files, Long productId) {
		// TODO Auto-generated method stub
		
		Product productById = productService.getProductById(productId);
		
		List<ImageDto> savedImageDto = new ArrayList<ImageDto>();
		
		for (MultipartFile file : files) {
			
			try {
				
				Image image = new Image();
				
				image.setFileName(file.getOriginalFilename());
				image.setFileType(file.getContentType());
				
				
				image.setImage(file.getBytes());
				
				image.setProduct(productById);
				
				
				String downloadUrl = "/api/v1/image/image/download/" +image.getId();
				
				image.setDownloadURL(downloadUrl); 
				
				Image savedImage = imageRepository.save(image);
				
				savedImage.setDownloadURL("/api/v1/image/image/download/" +savedImage.getId());
				
				imageRepository.save(savedImage); 
				
				
				ImageDto imageDto = new ImageDto();
				imageDto.setImageId(savedImage.getId());
				imageDto.setImageName(savedImage.getFileName());
				imageDto.setDownloadURL(savedImage.getDownloadURL());
				
				savedImageDto.add(imageDto); 
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		return savedImageDto;
	}

	@Override
	public void updateImage(MultipartFile file, Long imageId) {
		// TODO Auto-generated method stub
		
		Image image = getImageById(imageId);
		
		try {
			image.setFileName(file.getOriginalFilename());
			image.setFileType(image.getFileType());
//			image.setImage(new SerialBlob(file.getBytes()));
			imageRepository.save(image);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
