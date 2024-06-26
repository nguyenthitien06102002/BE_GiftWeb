package net.javaguides.giftbackend.controller;

import net.javaguides.giftbackend.dto.AddToCartRequest;
import net.javaguides.giftbackend.dto.CartItem;
import net.javaguides.giftbackend.dto.ProductsDto;
import net.javaguides.giftbackend.entity.Products;
import net.javaguides.giftbackend.entity.Users;
import net.javaguides.giftbackend.repository.ProductsRepository;
import net.javaguides.giftbackend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ProductsRepository productsRepository;


    private List<CartItem> cart = new ArrayList<>();

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartRequest request) {
        Long userID = request.getUserID().getId();
        Users user = usersRepository.findById(userID).orElse(null);
        Long productID = request.getProduct().getId();
        Products product = productsRepository.findById(productID).orElse(null);
        int quantity = request.getQuantity();
        if(user == null || product == null) {
            return ResponseEntity.badRequest().body("ID người dùng và ID sản phẩm là bắt buộc");
        }
        // Kiểm tra số lượng sản phẩm có đủ không
        if(product.getStock() < quantity) {
            return ResponseEntity.badRequest().body("Số lượng sản phẩm trong kho không đủ");
        }
        // Kiểm tra sản phẩm đã tồn tại trong giỏ hàng của người dùng chưa
        for (CartItem item : cart) {
            if (item.getUserID().getId().equals(userID) && item.getProduct().getId().equals(productID)) {
                item.setQuantity(item.getQuantity() + quantity);
                return ResponseEntity.ok("Product has been updated in the cart");
            }
        }

        // Nếu sản phẩm chưa có trong giỏ hàng của người dùng, thêm mới vào
        CartItem newItem = new CartItem();
        newItem.setUserID(user);
        newItem.setProduct(product);
        newItem.setQuantity(quantity);
        cart.add(newItem);
        return ResponseEntity.ok("Sản phẩm đã được thêm vào giỏ hàng");
    }

 @GetMapping("/items")
    public ResponseEntity<List<CartItem>> getCartItems() {
        return ResponseEntity.ok(cart);
    }


    @GetMapping("/items/{id}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable("id") Long userId) {

        List<CartItem> userCartItems = new ArrayList<>();

        for (CartItem item : cart) {
            if (item.getUserID().getId().equals(userId)) {
                userCartItems.add(item);
            }
        }
        return ResponseEntity.ok(userCartItems);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart() {
        cart.clear();
        return ResponseEntity.ok("Giỏ hàng đã được xóa");
    }

    @DeleteMapping("/clear/{productId}")
    public ResponseEntity<String> clearCartItem(@PathVariable Long productId) {
        // Xóa sản phẩm từ giỏ hàng dựa trên ID sản phẩm
        cart.removeIf(cartItem -> cartItem.getProduct().getId().equals(productId));
        return ResponseEntity.ok("Sản phẩm đã được xóa khỏi giỏ hàng");
    }

    @DeleteMapping("/clear/{userID}/{productId}")
    public ResponseEntity<String> clearCartItemByUserIDAndProductID(@PathVariable Long userID, @PathVariable Long productId) {
        // Xóa sản phẩm từ giỏ hàng dựa trên cả userID và productID
        cart.removeIf(cartItem -> cartItem.getUserID().getId() != null && cartItem.getUserID().getId().equals(userID) && cartItem.getProduct().getId().equals(productId));
        return ResponseEntity.ok("Sản phẩm có ID " + productId + " trong giỏ hàng của người dùng có ID " + userID + " đã được xóa");
    }

    //xoa tho ID
    @DeleteMapping("/clear/user/{userID}")
    public ResponseEntity<String> clearCartByUserID(@PathVariable Long userID) {
        // Xóa các mục trong giỏ hàng dựa trên user
        cart.removeIf(cartItem -> cartItem.getUserID().getId() != null && cartItem.getUserID().getId().equals(userID));
        return ResponseEntity.ok("Giỏ hàng của người dùng có ID " + userID + " đã được xóa");
             }
}