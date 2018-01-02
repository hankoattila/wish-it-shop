
var app = app || {};

app.logic = {

    addClickListener: function () {
        $("#board").on("click", ".cell", function(ev) {
            ev.stopPropagation();
            var cellNumber = $(this).attr("id").replace("cell-", "");
            app.dataHandler.changeBoard(cellNumber);
        });
    },

    generateTurnMessage: function () {
        console.log("generate");
        var turnSignEl = $("#turn-sign");
        if (currentPlayer.sign === "O") {
            turnSignEl.addClass("blue");
            turnSignEl.removeClass("red");
            turnSignEl.text("O");
        } else {
            turnSignEl.addClass("red");
            turnSignEl.removeClass("blue");
            turnSignEl.text("X");
        }
    },

    handleChangeBoardSuccess: function (response) {
        console.log(response);

        if (response.playerMove === null) {
            return;
        }

        var playerCell = $("#cell-" + response.playerMove.cellNumber);
        playerCell.removeClass("cell");
        playerCell.addClass("closed");
        switch (response.playerMove.sign) {
            case "O": playerCell.html(`<i class="fa fa-circle-o" aria-hidden="true"></i>`); break;
            case "X": playerCell.html(`<i class="fa fa-times" aria-hidden="true"></i>`); break;
        }

        var turnMsg;
        var turnSignEl = $("#turn-sign");
        if (response.playerMove.sign === "O") {
            turnMsg = "Current turn: ";
            turnSignEl.addClass("red");
            turnSignEl.removeClass("blue");
            turnSignEl.text("X");
        } else {
            turnMsg = "Current turn: ";
            turnSignEl.addClass("blue");
            turnSignEl.removeClass("red");
            turnSignEl.text("O");
        }
        //$("#turn").text(turnMsg);

        if (response.aiMove !== null) {
            var aiCell = $("#cell-" + response.aiMove.cellNumber);
            aiCell.removeClass("cell");
            aiCell.addClass("closed");
            aiCell.html(`<i class="fa fa-times" aria-hidden="true"></i>`);
        }

        var gameOverMsg = null;
        if (response.draw === true) {
            gameOverMsg = "Game is draw. Click the button to play again.";
        } else if (response.winner !== null) {
            gameOverMsg = response.winner.userName + " has won. Click the button to play again.";
        }

        if (gameOverMsg !== null) {
            $("#gameOverModal p").text(gameOverMsg);
            $("#gameOverModal").modal();
        }

    },

    handleChangeBoardError: function () {
        console.log("Error occurred.")
    }

};