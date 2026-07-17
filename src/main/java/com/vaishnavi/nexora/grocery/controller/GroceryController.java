package com.vaishnavi.nexora.grocery.controller;


import com.vaishnavi.nexora.grocery.dto.GroceryRequest;
import com.vaishnavi.nexora.grocery.dto.GroceryResponse;
import com.vaishnavi.nexora.grocery.entity.GroceryStatus;
import com.vaishnavi.nexora.grocery.entity.Priority;
import com.vaishnavi.nexora.grocery.service.GroceryService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/groceries")
public class GroceryController {



    @Autowired
    private GroceryService groceryService;







    // ================= CREATE GROCERY =================


    @PostMapping
    public ResponseEntity<GroceryResponse> createGrocery(

            @Valid @RequestBody GroceryRequest request,

            Authentication authentication

    ){


        return new ResponseEntity<>(

                groceryService.createGrocery(
                        request,
                        authentication.getName()
                ),

                HttpStatus.CREATED

        );

    }








    // ================= GET ALL + PAGINATION + SORT =================
    //
    // Example:
    // /groceries?page=0&size=10&sortBy=createdAt&direction=desc


    @GetMapping
    public ResponseEntity<Page<GroceryResponse>> getGroceries(

            Authentication authentication,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "createdAt") String sortBy,

            @RequestParam(defaultValue = "desc") String direction

    ){


        return ResponseEntity.ok(

                groceryService.getUserGroceries(

                        authentication.getName(),

                        page,

                        size,

                        sortBy,

                        direction

                )

        );

    }








    // ================= GET BY ID =================


    @GetMapping("/{id}")
    public ResponseEntity<GroceryResponse> getById(

            @PathVariable Long id,

            Authentication authentication

    ){


        return ResponseEntity.ok(

                groceryService.getById(

                        id,

                        authentication.getName()

                )

        );

    }








    // ================= UPDATE =================


    @PutMapping("/{id}")
    public ResponseEntity<GroceryResponse> updateGrocery(

            @PathVariable Long id,

            @Valid @RequestBody GroceryRequest request,

            Authentication authentication

    ){


        return ResponseEntity.ok(

                groceryService.updateGrocery(

                        id,

                        request,

                        authentication.getName()

                )

        );

    }








    // ================= MARK PURCHASED =================


    @PutMapping("/{id}/purchase")
    public ResponseEntity<GroceryResponse> markPurchased(

            @PathVariable Long id,

            Authentication authentication

    ){


        return ResponseEntity.ok(

                groceryService.markPurchased(

                        id,

                        authentication.getName()

                )

        );

    }








    // ================= DELETE =================


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGrocery(

            @PathVariable Long id,

            Authentication authentication

    ){


        groceryService.deleteGrocery(

                id,

                authentication.getName()

        );


        return ResponseEntity.ok(
                "Grocery item deleted successfully"
        );

    }








    // ================= SEARCH =================
    //
    // /groceries/search?keyword=milk&page=0&size=10


    @GetMapping("/search")
    public ResponseEntity<Page<GroceryResponse>> search(

            Authentication authentication,

            @RequestParam String keyword,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size

    ){


        return ResponseEntity.ok(

                groceryService.search(

                        authentication.getName(),

                        keyword,

                        page,

                        size

                )

        );

    }








    // ================= CATEGORY FILTER =================
    //
    // /groceries/category/DAIRY


    @GetMapping("/category/{category}")
    public ResponseEntity<Page<GroceryResponse>> getByCategory(

            @PathVariable String category,

            Authentication authentication,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size

    ){


        return ResponseEntity.ok(

                groceryService.getByCategory(

                        authentication.getName(),

                        category,

                        page,

                        size

                )

        );

    }








    // ================= STATUS FILTER =================
    //
    // /groceries/status/PURCHASED


    @GetMapping("/status/{status}")
    public ResponseEntity<Page<GroceryResponse>> getByStatus(

            @PathVariable GroceryStatus status,

            Authentication authentication,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size

    ){


        return ResponseEntity.ok(

                groceryService.getByStatus(

                        authentication.getName(),

                        status,

                        page,

                        size

                )

        );

    }








    // ================= PRIORITY FILTER =================
    //
    // /groceries/priority/HIGH


    @GetMapping("/priority/{priority}")
    public ResponseEntity<Page<GroceryResponse>> getByPriority(

            @PathVariable Priority priority,

            Authentication authentication,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size

    ){


        return ResponseEntity.ok(

                groceryService.getByPriority(

                        authentication.getName(),

                        priority,

                        page,

                        size

                )

        );

    }


}