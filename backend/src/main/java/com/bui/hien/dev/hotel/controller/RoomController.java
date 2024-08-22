package com.bui.hien.dev.hotel.controller;

import com.bui.hien.dev.hotel.dto.Response;
import com.bui.hien.dev.hotel.service.impl.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/rooms")
public class RoomController {


    @Autowired
    private RoomService roomService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> addNewRoom(
            @RequestParam(value = "photo", required = false) MultipartFile file,
            @RequestParam(value = "roomType", required = false) String roomType,
            @RequestParam(value = "roomPrice", required = false) BigDecimal roomPrice,
            @RequestParam(value = "roomDescription", required = false) String roomDescription
    ) {
        try {
            if (file == null || file.isEmpty() || roomType == null || roomType.isBlank() || roomPrice == null || roomType.isBlank()) {
                Response response = new Response();
                response.setStatusCode(400);
                response.setMessage("Please provide values for all fields(photo, roomType,roomPrice)");
                return ResponseEntity.status(response.getStatusCode()).body(response);
            }
            Response response = roomService.addNewRoom(file, roomType, roomPrice, roomDescription);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllRooms() {
        try {
            Response response = roomService.getAllRooms();
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
    }

    @GetMapping("/types")
    public Object getRoomTypes() {
        try {
            return roomService.getAllRoomTypes();
        } catch (Exception e) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
    }

    @GetMapping("/room-by-id/{roomId}")
    public ResponseEntity<Response> getRoomById(@PathVariable Long roomId) {
        try {
            Response response = roomService.getRoomById(roomId);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
    }

    @GetMapping("/all-available-rooms")
    public ResponseEntity<Response> getAvailableRooms() {
        try {
            Response response = roomService.getAllAvailableRooms();
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
    }

    @GetMapping("/available-rooms-by-date-and-type")
    public ResponseEntity<Response> getAvailableRoomsByDateAndType(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
            @RequestParam(required = false) String roomType
    ) {
        try {
            if (checkInDate == null || roomType == null || roomType.isBlank() || checkOutDate == null) {
                Response response = new Response();
                response.setStatusCode(400);
                response.setMessage("Please provide values for all fields(checkInDate, roomType,checkOutDate)");
                return ResponseEntity.status(response.getStatusCode()).body(response);
            }
            Response response = roomService.getAvailableRoomsByDataAndType(checkInDate, checkOutDate, roomType);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
    }

    @PutMapping("/update/{roomId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateRoom(@PathVariable Long roomId,
                                               @RequestParam(value = "photo", required = false) MultipartFile photo,
                                               @RequestParam(value = "roomType", required = false) String roomType,
                                               @RequestParam(value = "roomPrice", required = false) BigDecimal roomPrice,
                                               @RequestParam(value = "roomDescription", required = false) String roomDescription

    ) {
        try {
            Response response = roomService.updateRoom(roomId, roomDescription, roomType, roomPrice, photo);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
    }

    @DeleteMapping("/delete/{roomId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteRoom(@PathVariable Long roomId) {
        try {
            Response response = roomService.deleteRoom(roomId);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }

    }


}
