package com.vaishnavi.nexora.grocery.service;


import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.exception.ResourceNotFoundException;
import com.vaishnavi.nexora.grocery.dto.GroceryRequest;
import com.vaishnavi.nexora.grocery.dto.GroceryResponse;
import com.vaishnavi.nexora.grocery.entity.Grocery;
import com.vaishnavi.nexora.grocery.entity.GroceryStatus;
import com.vaishnavi.nexora.grocery.entity.Priority;
import com.vaishnavi.nexora.grocery.repository.GroceryRepository;
import com.vaishnavi.nexora.repository.UserRepository;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;



@Service
public class GroceryService {



    private final GroceryRepository groceryRepository;

    private final UserRepository userRepository;



    public GroceryService(
            GroceryRepository groceryRepository,
            UserRepository userRepository
    ){

        this.groceryRepository = groceryRepository;
        this.userRepository = userRepository;

    }







    // ================= CREATE GROCERY =================


    public GroceryResponse createGrocery(
            GroceryRequest request,
            String email
    ){


        User user = getUser(email);



        Grocery grocery = new Grocery();


        grocery.setItemName(request.getItemName());

        grocery.setQuantity(request.getQuantity());

        grocery.setUnit(request.getUnit());

        grocery.setCategory(request.getCategory());

        grocery.setPriority(
                request.getPriority()!=null
                        ?
                        request.getPriority()
                        :
                        Priority.MEDIUM
        );


        grocery.setEstimatedPrice(
                request.getEstimatedPrice()
        );


        grocery.setActualPrice(
                request.getActualPrice()
        );


        grocery.setStatus(
                request.getStatus()!=null
                        ?
                        request.getStatus()
                        :
                        GroceryStatus.PENDING
        );


        grocery.setUser(user);



        return mapToResponse(
                groceryRepository.save(grocery)
        );

    }








    // ================= PAGINATION + SORT =================


    public Page<GroceryResponse> getUserGroceries(
            String email,
            int page,
            int size,
            String sortBy,
            String direction
    ){


        User user=getUser(email);



        Sort sort =
                direction.equalsIgnoreCase("asc")
                        ?
                        Sort.by(sortBy).ascending()
                        :
                        Sort.by(sortBy).descending();



        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        sort
                );



        return groceryRepository
                .findByUser(user,pageable)
                .map(this::mapToResponse);

    }








    // ================= GET BY ID =================


    public GroceryResponse getById(
            Long id,
            String email
    ){


        User user=getUser(email);



        Grocery grocery =
                groceryRepository
                        .findByIdAndUser(id,user)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Grocery item not found"
                                ));



        return mapToResponse(grocery);

    }








    // ================= UPDATE =================


    public GroceryResponse updateGrocery(
            Long id,
            GroceryRequest request,
            String email
    ){


        User user=getUser(email);



        Grocery grocery =
                groceryRepository
                        .findByIdAndUser(id,user)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Grocery item not found"
                                ));




        grocery.setItemName(request.getItemName());

        grocery.setQuantity(request.getQuantity());

        grocery.setUnit(request.getUnit());

        grocery.setCategory(request.getCategory());

        grocery.setPriority(request.getPriority());

        grocery.setEstimatedPrice(request.getEstimatedPrice());

        grocery.setActualPrice(request.getActualPrice());



        if(request.getStatus()!=null){

            grocery.setStatus(request.getStatus());

        }



        return mapToResponse(
                groceryRepository.save(grocery)
        );

    }








    // ================= PURCHASED =================


    public GroceryResponse markPurchased(
            Long id,
            String email
    ){


        User user=getUser(email);



        Grocery grocery =
                groceryRepository
                        .findByIdAndUser(id,user)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Grocery item not found"
                                ));



        grocery.setStatus(
                GroceryStatus.PURCHASED
        );



        return mapToResponse(
                groceryRepository.save(grocery)
        );

    }








    // ================= DELETE =================


    public void deleteGrocery(
            Long id,
            String email
    ){


        User user=getUser(email);



        Grocery grocery =
                groceryRepository
                        .findByIdAndUser(id,user)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Grocery item not found"
                                ));



        groceryRepository.delete(grocery);

    }








    // ================= SEARCH =================


    public Page<GroceryResponse> search(
            String email,
            String keyword,
            int page,
            int size
    ){


        User user=getUser(email);



        Pageable pageable =
                PageRequest.of(page,size);



        return groceryRepository
                .findByUserAndItemNameContainingIgnoreCaseOrUserAndCategoryContainingIgnoreCase(
                        user,
                        keyword,
                        user,
                        keyword,
                        pageable
                )
                .map(this::mapToResponse);

    }








    // ================= CATEGORY FILTER =================


    public Page<GroceryResponse> getByCategory(
            String email,
            String category,
            int page,
            int size
    ){


        User user=getUser(email);



        Pageable pageable =
                PageRequest.of(page,size);



        return groceryRepository
                .findByUserAndCategory(
                        user,
                        category,
                        pageable
                )
                .map(this::mapToResponse);

    }








    // ================= STATUS FILTER =================


    public Page<GroceryResponse> getByStatus(
            String email,
            GroceryStatus status,
            int page,
            int size
    ){


        User user=getUser(email);



        Pageable pageable =
                PageRequest.of(page,size);



        return groceryRepository
                .findByUserAndStatus(
                        user,
                        status,
                        pageable
                )
                .map(this::mapToResponse);

    }








    // ================= PRIORITY FILTER =================


    public Page<GroceryResponse> getByPriority(
            String email,
            Priority priority,
            int page,
            int size
    ){


        User user=getUser(email);



        Pageable pageable =
                PageRequest.of(page,size);



        return groceryRepository
                .findByUserAndPriority(
                        user,
                        priority,
                        pageable
                )
                .map(this::mapToResponse);

    }








    // ================= USER =================


    private User getUser(String email){


        return userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));

    }








    // ================= ENTITY → DTO =================


    private GroceryResponse mapToResponse(
            Grocery grocery
    ){


        return new GroceryResponse(

                grocery.getId(),

                grocery.getItemName(),

                grocery.getQuantity(),

                grocery.getUnit(),

                grocery.getCategory(),

                grocery.getStatus(),

                grocery.getPriority(),

                grocery.getEstimatedPrice(),

                grocery.getActualPrice(),

                grocery.getCreatedAt(),

                grocery.getUpdatedAt()

        );

    }


}