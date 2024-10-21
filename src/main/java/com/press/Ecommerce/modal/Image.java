package com.press.Ecommerce.modal;

import java.io.ByteArrayInputStream;
import java.sql.Blob;

import javax.sql.rowset.serial.SerialBlob;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fileName;
	
	private String fileType;
	
	@JsonIgnore
	@Lob // Hibernate will map this to a BLOB column
    private byte[] image; 
	
	private String downloadURL;
	
	
//	@ManyToOne
//	@JoinColumn(name = "product_id")
//	private Product  product; 
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore // This will prevent the product field from being serialized
    private Product product;
	

}
