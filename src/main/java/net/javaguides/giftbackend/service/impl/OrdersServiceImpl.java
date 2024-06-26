package net.javaguides.giftbackend.service.impl;

import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.ImgProductDto;
import net.javaguides.giftbackend.dto.OrdersDto;
import net.javaguides.giftbackend.dto.ProductsDto;
import net.javaguides.giftbackend.dto.UsersDto;
import net.javaguides.giftbackend.entity.*;
import net.javaguides.giftbackend.exception.ResourceNotFoundException;
import net.javaguides.giftbackend.mapper.ImgProductMapper;
import net.javaguides.giftbackend.mapper.OrdersMapper;
import net.javaguides.giftbackend.mapper.ProductsMapper;
import net.javaguides.giftbackend.mapper.UsersMapper;
import net.javaguides.giftbackend.repository.*;
import net.javaguides.giftbackend.service.OrdersService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private OrdersRepository ordersRepository;
    private DiscountRepository discountRepository;
    private ProvinceRepository provinceRepository;
    private DistrictsRepository districtsRepository;
    private UsersRepository usersRepository;
    private StatusOrderRepository statusOrderRepository;
    private PaymentMethodRepository paymentMethodRepository;
    private ProductsRepository productsRepository;


@Override
public OrdersDto createOrder(OrdersDto ordersDto) {
    Long discountId = ordersDto.getDiscountId() != null ? ordersDto.getDiscountId().getId() : null;
    Long provinceId = ordersDto.getProvinceId().getProvinceID();
    Long districtId = ordersDto.getDistrictId().getDistrictID();
    Long userId = ordersDto.getUserId().getId();
    Long statusId = ordersDto.getStatus().getId();
    Long paymentId = ordersDto.getPaymentId().getId();
    Discount discount = discountId != null ? discountRepository.findById(discountId).orElse(null) : null;
    Provinces province = provinceRepository.findById(provinceId).orElse(null);
    Districts district = districtsRepository.findById(districtId).orElse(null);
    Users user = usersRepository.findById(userId).orElse(null);
    StatusOrder status = statusOrderRepository.findById(statusId).orElse(null);
    PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentId).orElse(null);
    if(province == null || district == null || user == null ){
        return null;
    }
    Orders orders = OrdersMapper.mapToOrders(ordersDto);
    orders.setDiscountId(discount);
    orders.setProvinceId(province);
    orders.setDistrictId(district);
    orders.setUserId(user);
    orders.setStatus(status);
    orders.setPaymentId(paymentMethod);
    Orders saveOrder = ordersRepository.save(orders);

    return OrdersMapper.mapToOrdersDto(saveOrder);
}



    @Override
    public List<OrdersDto> getOrderByIdUser(Users usersId) {
        List<Orders> orders = ordersRepository.findByUserId(usersId);
        if(orders.isEmpty()){
            throw new ResourceNotFoundException("No orders found for user with ID: " + usersId.getId());
        }
        return orders.stream()
                .map(OrdersMapper::mapToOrdersDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrdersDto updateStatusOrder(Long orderId, StatusOrder statusId) {
    Long id = statusId.getId();
    Orders orders = ordersRepository.findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
    StatusOrder statusOrder = statusOrderRepository.findById(id).orElse(null);
    if(statusOrder == null){
        return null;
    }
    orders.setStatus(statusOrder);
    Orders updatedOrderObj = ordersRepository.save(orders);
        return OrdersMapper.mapToOrdersDto(updatedOrderObj);
    }

    @Override
    public List<OrdersDto> getAllOrders() {
        List<Orders> orders = ordersRepository.findAll();
        return orders.stream()
                .map(OrdersMapper::mapToOrdersDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<OrdersDto> getOrderByDiscountId(Discount discountId) {
    Long id = discountId.getId();
    Discount discount = discountRepository.findById(id).orElse(null);
    if(discount == null){
        return null;
    }
    List<Orders> orders = ordersRepository.findByDiscountId(discount);
    if(orders.isEmpty()){
        return null;
    }
    return orders.stream()
            .map(OrdersMapper::mapToOrdersDto)
            .collect(Collectors.toList());

    }

    @Override
    public List<Double> getRevenueByMonth(int year) {
    List<Double> rerevenueByMonth = new ArrayList<>();
    for(int i = 1; i <= 12; i++) {
        Timestamp startOfMonth = Timestamp.valueOf(year + "-" + i + "-01 00:00:00");
        Timestamp endOfMonth = Timestamp.valueOf(year + "-" + i + "-31 23:59:59");
        List<Orders> orders = ordersRepository.findByCreateTimeBetween(startOfMonth, endOfMonth);
        double revenue = orders.stream().mapToDouble(Orders::getTotalPrice).sum();
        rerevenueByMonth.add(revenue);
    }
        return rerevenueByMonth;
    }

    @Override
    public Map<Integer, Double> getAnnualRevenue() {
    List<Object[]> results = ordersRepository.findAnnualRevenue();
    Map<Integer, Double> revenueByYear = new HashMap<>();
    for(Object[] result : results){
        Integer year = (Integer) result[0];
        double revenue = (double) result[1];
        revenueByYear.put(year, revenue);
    }
        return revenueByYear;
    }

    @Override
    public List<Double> getOrderByMonth(int year) {

        List<Double> orderByMonth = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            Timestamp startOfMonth = Timestamp.valueOf(year + "-" + i + "-01 00:00:00");
            Timestamp endOfMonth = Timestamp.valueOf(year + "-" + i + "-31 23:59:59");
            List<Orders> orders = ordersRepository.findByCreateTimeBetween(startOfMonth, endOfMonth);
            double revenue = orders.stream().mapToDouble(Orders::getId).count();
            orderByMonth.add(revenue);
        }
        return orderByMonth;
    }

    @Override
    public Map<String, Double> getCurrentAndPreviousMonthRevenue() {
    List<Object[]> results = ordersRepository.findCurrentAndPreviousMonthRevenue();
    Map<String, Double> revenueMap  = new HashMap<>();
        if (!results.isEmpty()) {
            Object[] row = results.get(0);
            revenueMap.put("currentMonthRevenue", ((Number) row[0]).doubleValue());
            revenueMap.put("previousMonthRevenue", ((Number) row[1]).doubleValue());
        }
        return revenueMap;
    }

    public Map<String, Long> getCurrentAndPreviousMonthOrders() {
        List<Object[]> results = ordersRepository.findCurrentAndPreviousMonthOrders();
        Map<String, Long> revenueMap = new HashMap<>();
        if (!results.isEmpty()) {
            Object[] result = results.get(0);
            Long currentMonthOrder = ((Number) result[0]).longValue();
            Long previousMonthOrder = ((Number) result[1]).longValue();
            revenueMap.put("currentMonthOrder", currentMonthOrder);
            revenueMap.put("previousMonthOrder", previousMonthOrder);
        }
        return revenueMap;
    }

    @Override
    public List<Map<String, Object>> getProductsCreatedBeforeCurrentMonth() {
        List<Object[]> results = ordersRepository.findProductsCreatedBeforeCurrentMonth();
        List<Map<String, Object>> products = new ArrayList<>();
        for (Object[] result : results) {
            Map<String, Object> productData = new HashMap<>();
            productData.put("productId", result[0]);
            productData.put("productName", result[1]);
            productData.put("imgPath", result[2]);
            productData.put("totalSold", result[3]);
            products.add(productData);
        }

        return products;
    }

    @Override
    public OrdersDto deleteOrder(Long orderId) {
        Orders orders = ordersRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Order not found with id: " + orderId));
        ordersRepository.delete(orders);
        return OrdersMapper.mapToOrdersDto(orders);
    }


}




