package se.checkoutspring.app.mapper;

import se.checkoutspring.app.dto.ItemRequest;
import se.checkoutspring.app.dto.ItemResponse;
import se.checkoutspring.app.model.Item;

public class ItemMapper {

    public static ItemResponse toItemResponse(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getProductId(),
                item.getPrice()
        );
    }

    public static Item toItem(ItemRequest itemRequest) {
        Item item = new Item();
        item.setProductId(itemRequest.productId());
        item.setPrice(itemRequest.price());
        return item;
    }
}
