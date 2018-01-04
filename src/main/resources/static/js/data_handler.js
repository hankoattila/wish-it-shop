
var app = app || {};

app.dataHandler = {

    addToCart: function (productId, quantity) {

        $.ajax({
            url: '/api/add-to-cart',
            method: 'POST',
            dataType: 'json',
            data: {
                product_id: productId
            },
            success: function(response) {
                app.productLogic.handleAddToCartSuccess(response, productId);
            },
            error: function() {
                app.productLogic.handleAddToCartError(productId);
            }
        });

    },

    removeLineItem: function (productId) {

        $.ajax({
            url: '/api/remove-line-item',
            method: 'POST',
            dataType: 'json',
            data: {
                product_id: productId
            },
            success: function(response) {
                app.productLogic.handleRemoveLineItemSuccess(response, productId);
            },
            error: function() {
                app.productLogic.handleRemoveLineItemError();
            }
        });

    }

};