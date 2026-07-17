package com.vaishnavi.nexora.calendar.controller;


import com.vaishnavi.nexora.calendar.dto.EventRequest;
import com.vaishnavi.nexora.calendar.dto.EventResponse;
import com.vaishnavi.nexora.calendar.service.EventService;


import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/events")
public class EventController {




    @Autowired
    private EventService eventService;







    // ================= CREATE EVENT =================


    @PostMapping
    public ResponseEntity<EventResponse> createEvent(

            @Valid @RequestBody EventRequest request,

            Authentication authentication

    ){


        return new ResponseEntity<>(

                eventService.createEvent(

                        request,

                        authentication.getName()

                ),

                HttpStatus.CREATED

        );

    }








    // ================= GET ALL EVENTS =================
    //
    // Example:
    // /events?page=0&size=10&sortBy=eventDate&direction=asc


    @GetMapping
    public ResponseEntity<Page<EventResponse>> getEvents(

            Authentication authentication,


            @RequestParam(defaultValue = "0")
            int page,


            @RequestParam(defaultValue = "10")
            int size,


            @RequestParam(defaultValue = "createdAt")
            String sortBy,


            @RequestParam(defaultValue = "desc")
            String direction

    ){


        return ResponseEntity.ok(

                eventService.getUserEvents(

                        authentication.getName(),

                        page,

                        size,

                        sortBy,

                        direction

                )

        );

    }








    // ================= GET EVENT BY ID =================


    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getById(

            @PathVariable Long id,

            Authentication authentication

    ){


        return ResponseEntity.ok(

                eventService.getById(

                        id,

                        authentication.getName()

                )

        );

    }








    // ================= UPDATE EVENT =================


    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEvent(

            @PathVariable Long id,


            @Valid @RequestBody EventRequest request,


            Authentication authentication

    ){


        return ResponseEntity.ok(

                eventService.updateEvent(

                        id,

                        request,

                        authentication.getName()

                )

        );

    }








    // ================= COMPLETE EVENT =================


    @PutMapping("/{id}/complete")
    public ResponseEntity<EventResponse> completeEvent(

            @PathVariable Long id,


            Authentication authentication

    ){


        return ResponseEntity.ok(

                eventService.completeEvent(

                        id,

                        authentication.getName()

                )

        );

    }








    // ================= DELETE EVENT =================


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(

            @PathVariable Long id,


            Authentication authentication

    ){


        eventService.deleteEvent(

                id,

                authentication.getName()

        );


        return ResponseEntity.ok(
                "Event deleted successfully"
        );

    }








    // ================= SEARCH =================
    //
    // /events/search?keyword=meeting&page=0&size=10


    @GetMapping("/search")
    public ResponseEntity<Page<EventResponse>> search(

            Authentication authentication,


            @RequestParam String keyword,


            @RequestParam(defaultValue = "0")
            int page,


            @RequestParam(defaultValue = "10")
            int size

    ){


        return ResponseEntity.ok(

                eventService.search(

                        authentication.getName(),

                        keyword,

                        page,

                        size

                )

        );

    }








    // ================= UPCOMING EVENTS =================
    //
    // /events/upcoming?page=0&size=10


    @GetMapping("/upcoming")
    public ResponseEntity<Page<EventResponse>> upcoming(

            Authentication authentication,


            @RequestParam(defaultValue = "0")
            int page,


            @RequestParam(defaultValue = "10")
            int size

    ){


        return ResponseEntity.ok(

                eventService.upcomingEvents(

                        authentication.getName(),

                        page,

                        size

                )

        );

    }








    // ================= STATUS FILTER =================
    //
    // completed=true
    //
    // /events/status?completed=true


    @GetMapping("/status")
    public ResponseEntity<Page<EventResponse>> status(

            Authentication authentication,


            @RequestParam boolean completed,


            @RequestParam(defaultValue = "0")
            int page,


            @RequestParam(defaultValue = "10")
            int size

    ){


        return ResponseEntity.ok(

                eventService.getByStatus(

                        authentication.getName(),

                        completed,

                        page,

                        size

                )

        );

    }


}