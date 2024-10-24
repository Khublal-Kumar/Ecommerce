package com.press.Ecommerce.modal;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class MyUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String LastName;
	
	private String eMail;
	
	private String password;
	
	@OneToOne(mappedBy = "myUser", cascade = CascadeType.ALL,orphanRemoval = true)
	private Cart cart;
	
	@OneToMany(mappedBy = "myUser", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Order> orders;

}
