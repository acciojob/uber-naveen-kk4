package com.driver.services.impl;

import com.driver.model.*;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);

	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
		Customer customer = customerRepository2.findById(customerId).get();
		customerRepository2.delete(customer);


	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
		List<Driver> driverList = driverRepository2.findAll();
		Driver driver = null;
		for(Driver drivers:driverList) {
			if (drivers.getCab().getAvailable()) {
				driver = drivers;
				break;
			}
		}
		if(Objects.isNull(driver))throw new Exception("No cab available!");
		driver.getCab().setAvailable(Boolean.FALSE);
		TripBooking tripBooking = new TripBooking();
		Customer customer = customerRepository2.findById(customerId).get();
		tripBooking.setCustomer(customer);
		tripBooking.setFromLocation(fromLocation);
		tripBooking.setToLocation(toLocation);
		tripBooking.setDistanceInKm(distanceInKm);
		tripBooking.setStatus(TripStatus.CONFIRMED);
		tripBooking.setDriver(driver);
		List<TripBooking> list1 = driver.getTripBookingList();
		list1.add(tripBooking);
		driver.setTripBookingList(list1);
		List<TripBooking> list2 = customer.getTripBookingLists();

		list2.add(tripBooking);
		customer.setTripBookingLists(list2);
		customerRepository2.save(customer);
		driverRepository2.save(driver);
		return tripBooking;







	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking tripBooking = tripBookingRepository2.findById(tripId).get();
		tripBooking.setStatus(TripStatus.CANCELED);
		tripBooking.setBill(0);
		Driver driver = tripBooking.getDriver();
		Cab cab = driver.getCab();
		cab.setAvailable(Boolean.TRUE);
		tripBookingRepository2.save(tripBooking);



	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking tripBooking = tripBookingRepository2.findById(tripId).get();
		tripBooking.setStatus(TripStatus.COMPLETED);
		Driver driver = tripBooking.getDriver();
		Cab cab = driver.getCab();
		cab.setAvailable(Boolean.TRUE);
		tripBooking.setBill(tripBooking.getDistanceInKm()*cab.getPerKmRate());
		tripBookingRepository2.save(tripBooking);


	}
}
