package net.javaguides.giftbackend.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.DiscountDto;
import net.javaguides.giftbackend.entity.Discount;
import net.javaguides.giftbackend.entity.Orders;
import net.javaguides.giftbackend.entity.Products;
import net.javaguides.giftbackend.entity.Users;
import net.javaguides.giftbackend.exception.ResourceNotFoundException;
import net.javaguides.giftbackend.mapper.CategoryMapper;
import net.javaguides.giftbackend.mapper.DiscountMapper;
import net.javaguides.giftbackend.mapper.ProductsMapper;
import net.javaguides.giftbackend.mapper.UsersMapper;
import net.javaguides.giftbackend.repository.DiscountRepository;
import net.javaguides.giftbackend.repository.OrdersRepository;
import net.javaguides.giftbackend.service.DiscountService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class DiscountServiceIpml implements DiscountService {
    private DiscountRepository discountRepository;
    private OrdersRepository ordersRepository;
    @Override
    public DiscountDto createDiscount(DiscountDto discountDto) {
        Discount discount = DiscountMapper.mapToDiscount(discountDto);
        Discount saveDiscount = discountRepository.save(discount);
        return DiscountMapper.mapToDiscountDto(saveDiscount);
    }

//    @Override
//    public DiscountDto applyDiscount(Long userId, String code) {
//       Optional<Discount> discountOptional = discountRepository.findByCode(code);
//       if(discountOptional.isPresent()){
//           Discount discount = discountOptional.get();
//           Long discountId = discount.getId();
//           if(isDiscountValid(discount)){
//
//               if(!doesUserHaveDiscount(userId, discount)){
//                   return DiscountMapper.mapToDiscountDto(discount);
//               }else {
//                   throw new ResourceNotFoundException("Discount code used");
//               }
//               return DiscountMapper.mapToDiscountDto(discount);
//
//           }else {
//               throw new ResourceNotFoundException("Discount code is not valid");
//           }
//
//
//        } else {
//           throw new ResourceNotFoundException("Discount code not found");
//       }
//
//    }
@Override
public DiscountDto applyDiscount(Users userId, String code) {
    Optional<Discount> discountOptional = discountRepository.findByCode(code);
    if(discountOptional.isPresent()){
        Discount discount = discountOptional.get();
        Long discountId = discount.getId();
        if(isDiscountValid(discount)){
            if(!doesUserHaveDiscount(userId, discount)){
                return DiscountMapper.mapToDiscountDto(discount);
            }else {
                throw new ResourceNotFoundException("Discount code used");
            }
        }else {
            throw new ResourceNotFoundException("Discount code is not valid");
        }
    } else {
        throw new ResourceNotFoundException("Discount code not found");
    }
}

    @Override
    public List<DiscountDto> getAllDiscounts() {
        List<Discount> discounts = discountRepository.findAll();
        List<Discount> activeDiscounts = discounts.stream()
                .filter(discount -> discount.isActive())
                .collect(Collectors.toList());
        return activeDiscounts.stream().map((discount) -> DiscountMapper.mapToDiscountDto(discount))
                .collect(Collectors.toList());

//        List<Products> allProducts = productsRepository.findAll();
//        List<Products> activeProducts = allProducts.stream()
//                .filter(product -> product.getStatus())
//                .collect(Collectors.toList());
//        return activeProducts.stream()
//                .map(product -> ProductsMapper.maptoProductsDto(product))
//                .collect(Collectors.toList());
    }

    @Override
    public DiscountDto deleteDiscount(Long discountId) {
        Discount discount = discountRepository.findById(discountId)
                .orElseThrow(() -> new ResourceNotFoundException("Discount not found with id: " + discountId));
        discount.setActive(false);
        Discount updateDiscount = discountRepository.save(discount);
        return DiscountMapper.mapToDiscountDto(updateDiscount);
    }

    @Override
    public DiscountDto updateDiscount(Long discountId, DiscountDto discountDto) {
        Discount discount = discountRepository.findById(discountId)
                .orElseThrow(() -> new ResourceNotFoundException("Discount not found with id: " + discountId));
        discount.setCode(discountDto.getCode());
        discount.setDiscountPercentage(discountDto.getDiscountPercentage());
        discount.setStartDate(discountDto.getStartDate());
        discount.setEndStart(discountDto.getEndStart());
        Discount updateDiscount = discountRepository.save(discount);
        return DiscountMapper.mapToDiscountDto(updateDiscount);
    }

    //kiem tra xem ma giam gia da đươc sư dung chưa
    public boolean doesUserHaveDiscount(Users userId, Discount discountId) {
        Orders order = ordersRepository.findByUserIdAndDiscountId(userId, discountId);
        return order != null;
    }

    // Phương thức kiểm tra xem mã giảm giá có hợp lệ không
    private boolean isDiscountValid(Discount discount) {
        return discount.getStartDate().before(new Timestamp(System.currentTimeMillis())) &&
                discount.getEndStart().after(new Timestamp(System.currentTimeMillis()));
    }
}
