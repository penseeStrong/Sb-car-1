package com.example.Sbcar1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

	class Car{
		private  String id;
		private String brand;
		private String model;
		private String color;
		private String registerNumber;
		private int year;
		private int price;

		public Car(String id, String brand, String model, String color, String registerNumber, int year, int price)
		{
			this.id=id;
			this.brand=brand;
			this.model=model;
			this.color=color;
			this.registerNumber=registerNumber;
			this.year=year;
			this.price=price;
		}

		public Car()
		{
			
		}

		public String getId(){
			return id;
		}
		public void setId(String id){
			this.id = id;
		}
		public String getBrand(){
			return brand;
		}
		public void setBrand(String brand){
			this.brand=brand;
		}
		public String getModel(){
			return model;
		}
		public void setModel(String model){
			this.model=model;
		}
		public String getColor(){
			return color;
		}
		public void setColor(String color){
			this.color=color;
		}
		public String getRegisterNumber(){
			return registerNumber;
		}
		public void setRegisterNumber(String registerNumber){
			this.registerNumber=registerNumber;
		}
		public int getYear(){
			return year;
		}
		public void setYear(int year){
			this.year=year;
		}
		public int getPrice(){
			return price;
		}
		public void setPrice(int price){
			this.price=price;
		}
		
	}

	@RestController
	@RequestMapping("/car")
	class CarController{

		private List<Car> voiture = new ArrayList<>();


		public CarController(){
			voiture.add(new Car("20014 14 74444", "Mercedes", "Benz", "Rouge", "BA 1010 A",25,  10000000));
			voiture.add(new Car("20014 25 10475", "BMW", "X10", "black", "BA 1010 A",25,  10000000));
			voiture.add(new Car("20014 10 20368", "CLIO", "lux", "jaune", "BA 1010 A",25,  10000000));

		}	
		
		@GetMapping("/obtenir")
		Iterable <Car> getCar(){
			return voiture;
		}
		
		@PostMapping("/create")
		public Car postCar(@RequestBody Car newCar) {
			voiture.add(newCar);
			
			return newCar;
		}

		@PutMapping("/update/{id}")
		ResponseEntity<Car> putCar(@PathVariable String id, @RequestBody Car newCar1) {
			int index = -1;
			for(Car c : voiture){
				if(c.getId().equals(id)){
					index = voiture.indexOf(c);
					voiture.set(index, newCar1);
				}
			}
			
	 		return (index == -1) ?
				new ResponseEntity<>(postCar(newCar1), HttpStatus.CREATED) :
				new ResponseEntity<>(newCar1, HttpStatus.OK);
		}
		
		@DeleteMapping("/delete/{id}")
		void  deleteCar(@PathVariable String id){
			voiture.removeIf(c -> c.getId().equals(id));
		}
		
		
}
	