package com.vaishnavi.nexora.common.service;


import com.vaishnavi.nexora.common.dto.PageRequestDTO;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;



@Service
public class PaginationService {



    public Pageable createPageable(PageRequestDTO request) {



        String sortBy = request.getSortBy();



        // Common default sorting
        if(sortBy == null ||
                sortBy.trim().isEmpty() ||
                sortBy.equalsIgnoreCase("string")) {


            sortBy = "id";

        }





        Sort sort;



        if(request.getDirection() != null &&
                request.getDirection()
                        .equalsIgnoreCase("asc")) {



            sort = Sort.by(sortBy)
                    .ascending();



        } else {



            sort = Sort.by(sortBy)
                    .descending();

        }





        return PageRequest.of(

                request.getPage(),

                request.getSize(),

                sort

        );

    }

}