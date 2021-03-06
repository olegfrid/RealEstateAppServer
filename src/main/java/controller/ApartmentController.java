package controller;

import domain.Apartment;
import domain.ApartmentTransfor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import Exceptions.NotAuthorizedUser;
import Exceptions.OnlyLandLoardCanDeleteHisAprtment;
import Service.ApartmentService;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @RequestMapping("/getAll")
    public @ResponseBody List<? extends Apartment> getAllApartments(@RequestHeader("token") String token){
        System.out.println("getAllApartments");
        return apartmentService.getAll(token);
    }

    
    @RequestMapping("/addNew")
    public @ResponseBody Boolean addNewWithUserPermissions(@RequestBody ApartmentTransfor apartment)
    				throws NotAuthorizedUser, IOException{
        return apartmentService.addNewWithUserPermissions(apartment);
    }
    @RequestMapping("/addImage")
    public @ResponseBody void addNewWithUserPermissions(
    		@RequestHeader (value="address") String address,
    		@RequestBody byte[] b)
    				throws NotAuthorizedUser{
    }
    
    @RequestMapping("/delete")
    public void delete(@RequestHeader("address") String address,
    					@RequestHeader("landLoardId") String token) throws OnlyLandLoardCanDeleteHisAprtment, NotAuthorizedUser{
    	apartmentService.delete(address, token);
    }
    
    @RequestMapping("/view")
    public void addView(@RequestHeader("address") String address){
    	apartmentService.addViewToApartment(address);
    }
    
    @RequestMapping("/getViews")
    public int getView(@RequestHeader("address") String address){
    	return apartmentService.getViewsForApartment(address);
    }


    @RequestMapping("/edit")
    public @ResponseBody Apartment edit(@RequestHeader("price") Integer price,
    		@RequestHeader(value = "isRent", required = false ) boolean isRent,
@RequestHeader("floor") Integer floor,@RequestHeader("elevator") Boolean elevator,@RequestHeader("constructionYear") Integer constructionYear,@RequestHeader("wareHouse") Boolean wareHouse,@RequestHeader("description") String description,@RequestHeader("size") Double size,@RequestHeader("averageRank") Double averageRank,@RequestHeader("address") String address,@RequestHeader("parking") Boolean parking,@RequestHeader("numToilet") Integer numToilet,@RequestHeader("numRooms") Integer numRooms,@RequestHeader("landLordID") String landLordID,@RequestHeader("image") byte[] image){
        System.out.println("edit apartment with address: "+address);
        return apartmentService.edit(price,floor,elevator,constructionYear,
        		wareHouse,description,size,address,parking,numToilet,numRooms,landLordID,image,isRent);
    }
    
    @GetMapping("/getByAddress")
    public @ResponseBody Apartment getByAddress(@RequestHeader("address") String address){
        return apartmentService.getByAddress(address);
    }
    
    @GetMapping("/getByLandLordID")
    public @ResponseBody List<Apartment> getByLandLordID(@RequestHeader("address") String address,
    												@RequestHeader("userId") int userId){
        return apartmentService.getByLandLordID(userId);
    }
    
    @GetMapping("/getByPrice")
    public @ResponseBody List<Apartment> getByPrice(@RequestHeader("address") String address,
			@RequestHeader("price") int price){
        return apartmentService.getByPrice(price);
    }  
    
    @GetMapping("/getByElevator")
    public @ResponseBody List<Apartment> getByElevator(@RequestHeader("address") String address,
			@RequestHeader("elevator") boolean elevator){
        return apartmentService.getByElevator(elevator);
    }  
    
    @GetMapping("/getByFloor")
    public @ResponseBody List<Apartment> getByFloor(@RequestHeader("address") String address,
			@RequestHeader("floor") int floor){
        return apartmentService.getByFloor(floor);
    }  
    
    @GetMapping("/getByConstructionYear")
    public @ResponseBody List<Apartment> getByConstructionYear(@RequestHeader("address") String address,
			@RequestHeader("constructionYear") int constructionYear){
        return apartmentService.getByConstructionYear(constructionYear);
    }  
    
    @GetMapping("/getBySize")
    public @ResponseBody List<Apartment> getBySize(@RequestHeader("address") String address,
			@RequestHeader("size") double size){
        return apartmentService.getBySize(size);
    }  
    
    @GetMapping("/getByParking")
    public @ResponseBody List<Apartment> getByParking(@RequestHeader("address") String address,
			@RequestHeader("parking") boolean parking){
        return apartmentService.getByParking(parking);
    }  
    
    @GetMapping("/getByWareHouse")
    public @ResponseBody List<Apartment> getByWareHouse(@RequestHeader("address") String address,
			@RequestHeader("wareHouse") Boolean wareHouse){
        return apartmentService.getByWareHouse(wareHouse);
    }  
    
    @GetMapping("/getByNumToilet")
    public @ResponseBody List<Apartment> getByNumToilet(@RequestHeader("address") String address,
			@RequestHeader("numToilet") int numToilet){
        return apartmentService.getByNumToilet(numToilet);
    }  
    
    @GetMapping("/getByNumRooms")
    public @ResponseBody List<Apartment> getByNumRooms(@RequestHeader("address") String address,
			@RequestHeader("numRooms") int numRooms){
        return apartmentService.getByNumRooms(numRooms);
    } 
    @PostMapping("/delete")
    public @ResponseBody boolean delete(@RequestHeader("address") String address){
        return apartmentService.delete(address);
    } 
}
