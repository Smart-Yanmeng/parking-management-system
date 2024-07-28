package com.qztc.parkingmanagementsystem.controller;
import com.qztc.parkingmanagementsystem.domain.dto.CreateOrderDto;
import com.qztc.parkingmanagementsystem.domain.dto.SettleOrderDto;
import com.qztc.parkingmanagementsystem.domain.dto.UpdateOrderDto;
import com.qztc.parkingmanagementsystem.domain.vo.ResultVo;
import com.qztc.parkingmanagementsystem.service.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author congyijiu
 * @create 2024-05-08-09:37
 */

@RestController
@RequestMapping("/order")
@Tag(name = "订单管理")
public class OrderController {

    @Resource
    private IOrderService orderService;


    @PostMapping("/createOrder")
    @Operation(summary = "创建订单")
    public ResultVo createOrder(@RequestBody @Valid CreateOrderDto createOrderDto) {
        return orderService.createOrder(createOrderDto);
    }

    @PostMapping("/updateOrder")
    @Operation(summary = "更新订单")
    public ResultVo updateOrder(@RequestBody @Valid UpdateOrderDto updateOrderDto) {
        return orderService.updateOrder(updateOrderDto);
    }

    @PostMapping("/settleOrder")
    @Operation(summary = "结算订单")
    public ResultVo settleOrder(@RequestBody @Valid SettleOrderDto settleOrderDto) {
        return orderService.settleOrder(settleOrderDto);
    }
}
