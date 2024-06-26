package net.javaguides.giftbackend.service.impl;

import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.ImgProductDto;
import net.javaguides.giftbackend.dto.OrderDetailDto;
import net.javaguides.giftbackend.dto.ProductsDto;
import net.javaguides.giftbackend.entity.*;
import net.javaguides.giftbackend.exception.ResourceNotFoundException;
import net.javaguides.giftbackend.mapper.ImgProductMapper;
import net.javaguides.giftbackend.mapper.OrderDetailMapper;
import net.javaguides.giftbackend.mapper.OrdersMapper;
import net.javaguides.giftbackend.mapper.ProductsMapper;
import net.javaguides.giftbackend.repository.ImgProductRepository;
import net.javaguides.giftbackend.repository.OrderDetailRepository;
import net.javaguides.giftbackend.repository.OrdersRepository;
import net.javaguides.giftbackend.repository.ProductsRepository;
import net.javaguides.giftbackend.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderDetailRepository orderDetailRepository;
    private OrdersRepository ordersRepository;
    private ProductsRepository productsRepository;
    private ImgProductRepository imgProductRepository;
    @Override
    public OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto) {
        Long orderId = orderDetailDto.getOrderId().getId();
        Long productId = orderDetailDto.getProductId().getId();
        Orders orders = ordersRepository.findById(orderId).orElse(null);
        Products products = productsRepository.findById(productId).orElse(null);
        if (orders == null || products == null) {
            return null;
        }
        // Kiểm tra số lượng sản phẩm trong kho có đủ không
        if (products.getStock() < orderDetailDto.getQuantity()) {
            throw new IllegalArgumentException("Số lượng sản phẩm trong kho không đủ");
        }
        OrderDetail orderDetail = OrderDetailMapper.mapToOrderDetai(orderDetailDto);
        orderDetail.setOrderId(orders);
        orderDetail.setProductId(products);
        OrderDetail saveOrderDetail = orderDetailRepository.save(orderDetail);
        updateProductQuantity(productId, orderDetailDto.getQuantity());
        return OrderDetailMapper.mapToOrderDetailDto(saveOrderDetail);
    }

//    @Override
//    public List<OrderDetailDto> getAllOrderId(Orders orderId){
//        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
//        if(orderDetails.isEmpty()){
//            throw new ResourceNotFoundException("No images found for product with ID: " + orderId.getId());
//
//        }
//        return orderDetails.stream()
//                .map(OrderDetailMapper::mapToOrderDetailDto)
//                .collect(Collectors.toList());
//    }
//
//
//
//    @Override
//    public Optional<Boolean> isOrderBelongsToUser(Long userId, Long orderId) {
//        Orders order = ordersRepository.findById(orderId).orElse(null);
//        if (order == null) {
//            return Optional.empty();
//        }
//        return Optional.of(order.getUserId().getId().equals(userId));
//    }

    @Override
    public List<OrderDetailDto> getAllOrderId(Long userId, Orders orderId) {
        // Kiểm tra xem userId có khớp với id trên param không
        if (!isUserIdMatchesOrder(userId, orderId)) {
            throw new IllegalArgumentException("User ID does not match the order ID");
        }

        // Lấy danh sách chi tiết đơn hàng theo orderId
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);

        // Kiểm tra xem danh sách chi tiết đơn hàng có trống không
        if (orderDetails.isEmpty()) {
            return null;
        }

        // Chuyển đổi danh sách chi tiết đơn hàng thành danh sách DTO và trả về
        return orderDetails.stream()
                .map(OrderDetailMapper::mapToOrderDetailDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailDto> getAllOrderDetailByOrderId(Orders orderId) {
        Long id = orderId.getId();
        Orders order = ordersRepository.findById(id).orElse(null);
        if (order == null) {
            return null;
        }
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(order);
        return orderDetails.stream()
                .map(OrderDetailMapper::mapToOrderDetailDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> getTopSellingProducts() {
        List<Object[]> results = orderDetailRepository.findTopSellingProducts();
        List<Map<String, Object>> topSellingProducts = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> productData = new HashMap<>();
            productData.put("productId", result[0]);
            productData.put("productName", result[1]);
            productData.put("imgPath", result[2]);
            productData.put("totalSold", result[3]);
            topSellingProducts.add(productData);
        }

        return topSellingProducts;
    }

    private boolean isUserIdMatchesOrder(Long userId, Orders orderId) {
        // Lấy userId từ orderId
        Long orderUserId = orderId.getUserId().getId();
        // So sánh userId với userId từ orderId
        return userId.equals(orderUserId);
    }

    public ProductsDto updateProductQuantity(Long productId, long quantity) {
        Products product = productsRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
        product.setStock(product.getStock() - quantity);
        Products updatedProduct = productsRepository.save(product);
        return ProductsMapper.maptoProductsDto(updatedProduct);

    }




}
