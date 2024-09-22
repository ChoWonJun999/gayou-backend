package com.gayou.route.controller;

import com.gayou.route.dto.RouteHeadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.gayou.route.service.RouteService;

import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    /**
     * 사용자의 경로를 저장하는 메서드
     *
     * @param email    - 현재 인증된 사용자의 사용자 이메일 (JWT 토큰에서 추출된 값)
     * @param routeDTO - 저장할 경로 정보 (RouteHeadDto)
     * @return ResponseEntity<Long> - 생성된 경로의 ID와 함께 HTTP 상태 코드를 반환
     */
    @PostMapping("/locations")
    public ResponseEntity<Long> saveCourse(@AuthenticationPrincipal String email,
            @RequestBody RouteHeadDto routeDTO) {

        Long routeHeadId = routeService.saveRoute(routeDTO, email);
        return new ResponseEntity<>(routeHeadId, HttpStatus.CREATED);
    }

    /**
     * 사용자가 저장한 경로 목록을 반환하는 메서드
     *
     * @param email - 현재 인증된 사용자의 사용자 이메일 (JWT 토큰에서 추출된 값)
     * @return ResponseEntity<List<RouteHeadDto>> - 사용자가 저장한 경로 목록을 반환
     */
    @GetMapping("/locations")
    public ResponseEntity<?> getMyCourse(@AuthenticationPrincipal String email) {
        List<RouteHeadDto> data = routeService.getMyRoute(email);
        return ResponseEntity.ok(data);
    }
}
