package se.checkoutspring.app.mapper;

import se.checkoutspring.app.dto.ItemRequest;
import se.checkoutspring.app.dto.ItemResponse;
import se.checkoutspring.app.dto.ProductResponse;
import se.checkoutspring.app.model.Item;

public class ItemMapper {

    public static ItemResponse toItemResponse(Item item, ProductResponse productDetails) {
        return new ItemResponse(
                item.getId(),
                item.getProductId(),
                productDetails.price(),
                productDetails.title(),
                productDetails.description(),
                productDetails.category(),
                productDetails.image()
        );
    }

    public static ItemResponse fromItemToResponse(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getProductId(),
                item.getPrice(),
                item.getTitle(),
                item.getDescription(),
                item.getCategory(),
                item.getImage()
        );
    }

    public static Item toItem(ItemRequest itemRequest) {
        Item item = new Item();
        item.setProductId(itemRequest.productId());

        return item;
    }

}
