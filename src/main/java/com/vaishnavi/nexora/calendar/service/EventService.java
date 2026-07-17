package com.vaishnavi.nexora.calendar.service;


import com.vaishnavi.nexora.calendar.dto.EventRequest;
import com.vaishnavi.nexora.calendar.dto.EventResponse;
import com.vaishnavi.nexora.calendar.entity.Event;
import com.vaishnavi.nexora.calendar.repository.EventRepository;

import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.exception.ResourceNotFoundException;
import com.vaishnavi.nexora.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;



@Service
public class EventService {




    @Autowired
    private EventRepository eventRepository;



    @Autowired
    private UserRepository userRepository;







    // ================= CREATE EVENT =================


    public EventResponse createEvent(
            EventRequest request,
            String email
    ){


        User user = getUser(email);



        Event event = new Event();


        event.setTitle(request.getTitle());

        event.setDescription(request.getDescription());

        event.setEventDate(request.getEventDate());

        event.setLocation(request.getLocation());

        event.setCompleted(request.isCompleted());

        event.setUser(user);



        return mapToResponse(
                eventRepository.save(event)
        );

    }








    // ================= PAGINATION + SORT =================


    public Page<EventResponse> getUserEvents(

            String email,

            int page,

            int size,

            String sortBy,

            String direction

    ){


        User user = getUser(email);



        Sort sort = direction.equalsIgnoreCase("asc")

                ? Sort.by(sortBy).ascending()

                : Sort.by(sortBy).descending();



        Pageable pageable =
                PageRequest.of(page,size,sort);



        return eventRepository
                .findByUser(user,pageable)
                .map(this::mapToResponse);

    }








    // ================= GET EVENT BY ID =================


    public EventResponse getById(
            Long id,
            String email
    ){


        User user = getUser(email);



        Event event =
                eventRepository.findByIdAndUser(id,user)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Event not found"
                                )
                        );



        return mapToResponse(event);

    }








    // ================= UPDATE EVENT =================


    public EventResponse updateEvent(

            Long id,

            EventRequest request,

            String email

    ){


        Event event = getEvent(id,email);



        event.setTitle(request.getTitle());

        event.setDescription(request.getDescription());

        event.setEventDate(request.getEventDate());

        event.setLocation(request.getLocation());

        event.setCompleted(request.isCompleted());



        return mapToResponse(
                eventRepository.save(event)
        );

    }








    // ================= COMPLETE EVENT =================


    public EventResponse completeEvent(

            Long id,

            String email

    ){


        Event event = getEvent(id,email);



        event.setCompleted(true);



        return mapToResponse(
                eventRepository.save(event)
        );

    }








    // ================= DELETE =================


    public void deleteEvent(

            Long id,

            String email

    ){


        Event event = getEvent(id,email);


        eventRepository.delete(event);

    }








    // ================= SEARCH =================


    public Page<EventResponse> search(

            String email,

            String keyword,

            int page,

            int size

    ){


        User user = getUser(email);



        Pageable pageable =
                PageRequest.of(page,size);



        return eventRepository
                .findByUserAndTitleContainingIgnoreCaseOrUserAndDescriptionContainingIgnoreCase(
                        user,
                        keyword,
                        user,
                        keyword,
                        pageable
                )
                .map(this::mapToResponse);

    }








    // ================= UPCOMING EVENTS =================


    public Page<EventResponse> upcomingEvents(

            String email,

            int page,

            int size

    ){


        User user=getUser(email);



        Pageable pageable =
                PageRequest.of(page,size);



        return eventRepository
                .findByUserAndEventDateAfter(
                        user,
                        LocalDateTime.now(),
                        pageable
                )
                .map(this::mapToResponse);

    }








    // ================= COMPLETED FILTER =================


    public Page<EventResponse> getByStatus(

            String email,

            boolean completed,

            int page,

            int size

    ){


        User user=getUser(email);



        Pageable pageable =
                PageRequest.of(page,size);



        return eventRepository
                .findByUserAndCompleted(
                        user,
                        completed,
                        pageable
                )
                .map(this::mapToResponse);

    }








    // ================= HELPERS =================


    private User getUser(String email){


        return userRepository.findByEmail(email)

                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "User not found"
                        )
                );

    }







    private Event getEvent(
            Long id,
            String email
    ){


        User user=getUser(email);



        return eventRepository
                .findByIdAndUser(id,user)

                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Event not found"
                        )
                );

    }








    // ================= ENTITY TO DTO =================


    private EventResponse mapToResponse(Event event){


        return new EventResponse(

                event.getId(),

                event.getTitle(),

                event.getDescription(),

                event.getEventDate(),

                event.getLocation(),

                event.isCompleted(),

                event.getCreatedAt(),

                event.getUpdatedAt()

        );

    }


}