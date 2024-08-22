package com.bui.hien.dev.hotel.service.interfac;

import com.bui.hien.dev.hotel.dto.LoginRequest;
import com.bui.hien.dev.hotel.dto.Response;
import com.bui.hien.dev.hotel.entity.User;

public interface IUserService {
    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUser();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String userId);
}
