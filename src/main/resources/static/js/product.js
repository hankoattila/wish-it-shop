
var app = app || {};

app.productLogic = {

    addToCartListener: function () {
        $("#products").on("click", ".add-to-cart", function(ev) {
            ev.stopPropagation();
            var productId = $(this).data("product-id");
            app.dataHandler.addToCart(productId);
        });
    },

    reviewRemoveItemListener: function () {
        $(".remove-img").on("click", function(ev) {
            ev.stopPropagation();
            var productId = $(this).data("product-id");
            app.dataHandler.removeLineItem(productId);
        });
    },

    handleAddToCartSuccess: function (response, productId) {
        console.log(response);
        if (response === "new_item") {
            app.utils.toastMessage("New item successfully added to cart.");
            var cartItemCount = $("#cart-item-count");
            cartItemCount.text(Number(cartItemCount.text()) + 1);
        } else {
            app.utils.toastMessage("Item could not be added to cart.");
        }
    },

    handleAddToCartError: function (productId) {
        app.utils.toastMessage("Failed to add to cart.");
    },

    handleRemoveLineItemSuccess: function (response, productId) {
        var resp = JSON.parse(response);
        $("#total-total").text(String(parseFloat(Math.round(resp.total * 100) / 100).toFixed(2)) + " USD");
        $("#line-item-" + productId).remove();
        app.utils.toastMessage("Item removed from cart.");

        var cartItemCounterEl = $("#cart-item-count");
        var refreshedValue = Number(cartItemCounterEl.text()) < 1 ? 0 : Number(cartItemCounterEl.text()) - 1;
        cartItemCounterEl.text(refreshedValue);

        if (resp.cartIsEmpty) {
            var table = $("#review-table");
            table.after(`<div><span id="empty-cart-label">Your cart is empty!</span></div>`);
            table.remove();
            $("#proceed-to-checkout").remove();
        }
    },

    handleRemoveLineItemError: function () {
        app.utils.toastMessage("Item could not be removed from cart.");
    }

};