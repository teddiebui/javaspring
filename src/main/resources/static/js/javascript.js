
$(document).ready(function(){
    const location = window.location.pathname;

    function doDate() {
        var now = new Date();
        $("#current-time").html(now.toUTCString());
    }

    setInterval(doDate, 1000)


    if (location === "/") {
        $.ajax({
            url: "/product/get-all",
            type: "GET",
            contentType: "application/json",
            success: (data, status, response) => {
                console.log("data: ", data);
                for (let i=0; i < data.length; i++) {
                    let name = data[i].name;
                    let price = data[i].price;
                    let id = data[i].id;
                    let rating = 0;
                    let imageUrl=data[i].imageUrl == undefined ? "https://via.placeholder.com/150" : data[i].imageUrl;
                    let html = '<div class="col-3 product">' +
                    '<a href="/product/' + id + '/' +'">' +
                    '<div class="img">' + '<img src="'+ imageUrl + '"></div>' +
                    '<div class="name">' + name + '</div>' +
                    '<div class="price">' + price + ' VND</div>' +
                    '<div class="rating">' + rating +"/5" + '</div>' +
                    '<button type="button" class="btn btn-primary rating">Mua ngay </button>' +
                    '</div>' +
                    '</a>';
                    $("#product-list").append(html);
                }
            },
            error: (err) => {console.log(err)}
        });

        $("#loginform button").click(()=> {
            const data = {};
            data["username"] = $("#l-username").val();
            data["password"] = $("#l-pw").val();
            const jsonData = JSON.stringify(data);
    
            $.ajax({
                url: "/login-handle",
                type: "POST",
                contentType: "application/json",
                data: jsonData,
                success: (data, status, response) => {
                    console.log("data: ", data);
                    loginSuccess();
                },
                error: (err) => {console.log(err)}


            });
        });
    
        $("#registrationform button").click(()=> {
            console.log("registration form clicked");
            const data = {};
            data["username"] = $("#r-username").val();
            data["password"] = $("#r-pw").val();
            data["fullName"] = $("#r-fullName").val();
            data["phoneNumber"] = $("#r-phoneNumber").val();
            data["email"] = $("#r-email").val();
            data["enabled"] = true;
            data["authorities"] = ["user", "admin"];
            const jsonData = JSON.stringify(data);
    
            $.ajax({
                url: "/register",
                type: "POST",
                contentType: "application/json",
                data: jsonData,
                success: (data, status, response) => {
                    console.log("data: ", data);
                    loginSuccess();
                },
                error: (err) => {console.log(err)}
    
            });
    
        });

        const loginSuccess = function() {
            let myHtml = '<div id="account-info">' +
                         '<button class="btn" type="button"><a href="/accountinfo">My Account</a></button>' +
                         '</div>';
            console.log(myHtml);

            $("#loginform").html("");

            $("#loginform").append(myHtml);

            $("#registrationform").css("display", "none");
        }

    }
    


    $("userinfo-form").click(()=>{
        console.log("registration form clicked");
        const data = {};
        data["username"] = $("#u-username").val();
        data["email"] = $("#u-email").val();
        data["enabled"] = $("#radio-true").is(":checked") ? 1 : 0;
        data["authorities"] = ["user"];
        const jsonData = JSON.stringify(data);
        console.log(data);
        // $.ajax({
        //     url: "/update",
        //     type: "POST",
        //     contentType: "application/json",
        //     data: jsonData,
        // })
    });

    if (location == "/accountinfo") {

        function callAjax(idString) {
           var url = "";
           switch (idString) {
           case "myOrders":
                url = "my orders";
                break;
           case "myReviews":
                url = "my reviews";
                break;
           case "myPaymentMethods":
                url = "my payment";
                break;
           case "myAddress":
                url = "my address";
                break;
           }
           console.log("sned ajax for id: " + url);
//           $.ajax({
//               url: url,
//               type: "GET",
//               contentType: "application/json",
//               data: jsonData,
//               success: (data, status, response) => {
//                   console.log("data: ", data);
//               },
//               error: (err) => {console.log(err)}
//           });
        }


        $(".list-group-item").each((i,e)=> {

            //add function click
            $(e).click((a) => {
                $(".list-group-item").removeClass("active");
                $(a.target).addClass("active");

                let idString = $(a.target).attr("id");



                //display html

                //step 1: reset
                $("body main div[target-id]").addClass("display-none");

                //step 2: display active
                $("body main div[target-id=" + idString +"]").removeClass("display-none");

                //step 3: call ajax;
                //call Ajax based on target id
                callAjax(idString);
                });


            });
    }

    if (location == "/admin") {
        //add event display modal
        $(".btn-modal").each((index,element)=>{
            $(element).click(()=>{

                if ($(element).attr("id") == "btn-add-product") {
                    $.ajax({
                        url: "/category/get-all",
                        type: "get",
                        success: (data, status, response) => {
                            console.log("data: ", data);
                            var myHtml = "";
                            for (let i=0; i < data.length ; i++ ) {
                                myHtml += '<option value ="' + data[i].id+'">' + data[i].name + '</option>';
                            }
                            $("#categories").html(myHtml);
                        },
                        error: (err) => {console.log(err)}

                    });
                }

                if ($(element).attr("id") == "btn-add-category") {
                    $.ajax({
                        url: "/category/get-all",
                        type: "get",
                        success: (data, status, response) => {
                            console.log("data: ", data);
                            var myHtml = "";
                            for (let i=0; i < data.length ; i++ ) {
                                myHtml += '<option value ="' + data[i].id+'">' + data[i].name + '</option>';
                            }
                            $("#categories").html(myHtml);
                        },
                        error: (err) => {console.log(err)}

                    });
                }

                $(".my-modal[id-target="+$(element).attr("id")+"]").show();
                $(".my-modal[id-target="+$(element).attr("id")+"] .modal-close").click(()=>{
                    $(".my-modal[id-target="+$(element).attr("id")+"]").hide();
                });


            });

        });

        //create ajax for modal add user
        $("#btn-add-user #btn-add-submit").click((e)=>{
            const data = {};
            data["username"] = $("#add-user-username").val();
            data["password"] = $("#add-user-password").val();
            data["fullName"] = $("#add-user-fullName").val();
            data["phoneNumber"] = $("#add-user-phoneNumber").val();
            data["email"] = $("#add-user-email").val();
            data["enabled"] = true;
            switch ($("authorities").val()) {
                case 'admin':
                    data["authorities"] = ["user", "admin"]
                    break;
                case 'user':
                    data["authorities"] = ["user"]
                    break;
            }
            const jsonData = JSON.stringify(data);

            $.ajax({
                url: "/user/add",
                type: "POST",
                contentType: "application/json",
                data: jsonData,
                success: (data, status, response) => {
                    console.log("data: ", data);
                    checkResponse(data);
                },
                error: (err) => {console.log(err)}

            });
        });

        $("#create-product-form .btn-submit").click(()=>{
        		const data = {};
        		data["name"] = $("#productName").val();
        		data["productCategory"] = $("#productCategory").val();
        		data["productPrice"] = $("#productPrice").val();
        		data["productDescription"] = $("#productDescription").val();
        		data["imageUrl"] = $("#productImage").val();
        		data["userId"] = $("#productCreator").val();
        		const jsonData = JSON.stringify(data);

        		console.log("jsonData: ", jsonData);

        		$.ajax({
        			url: "/product/add",
        			type: "POST",
        			contentType: "application/json",
        			data: jsonData,
        			success: (data, status, response) => {
        				console.log("data: ", data);
        				checkResponse(data);
        			},
        			error: (err) => {console.log(err)}
        		});
        	});

        	$("#create-category-form .btn-submit").click(()=>{
        		const data = {};
        		data["name"] = $("#categoryName").val();
                data["description"] = $("#categoryDescription").val();
                data["userId"] = $("#categoryCreator").val();
                data["parentCategory"] = $("#categoryParent").val()
        		const jsonData = JSON.stringify(data);

        		console.log("jsonData: ", jsonData);

        		$.ajax({
        			url: "/category/add",
        			type: "POST",
        			contentType: "application/json",
        			data: jsonData,
        			success: (data, status, response) => {
        				console.log("data: ", data);
        				checkResponse(data);
        			},
        			error: (err) => {console.log(err)}
        		});
        	});

        $("#user-search #btn-search").click(()=>{
            var data = "";
            var myUrl = "/user/get-all";
            var username = $("#user-search > input[type=text]").val();

            if (username != "") {
                myUrl = "/user/get";
                data = "username=" + username;
            }
            console.log(myUrl, data);
            $.ajax({
                url: myUrl,
                type: "GET",
                contentType: "application/json",
                dataType: "json",
                data: data,
                success: (data, status, response) => {
                    $("#list-user-management .data-details").html("");

                    $(data).each((index, dataObject)=> {
                        var myRow = '<div class="row">' +
                            '<div class="col">' +
                            '<a href="">' +
                            (dataObject.username == null ? "" : dataObject.username) +
                            '</a>' +
                            '</div>' +
                            '<div class="col">' + (dataObject.fullName == null ? "" : dataObject.fullName) + '</div>' +
                            '<div class="col">' + (dataObject.email == null ? "" : dataObject.email) + '</div>' +
                            '<div class="col">' + (dataObject.phoneNumber == null ? "" : dataObject.phoneNumber) + '</div>' +
                            '<div class="col">' + (dataObject.creationTime == null ? "" : dataObject.creationTime) + '</div>' +
                            '<div class="col">' + (dataObject.enabled == null ? "No" : "Yes") + '</div>' +
                            ''
                        '</div>'

                        console.log(myRow);
                        $("#list-user-management .data-details").append(myRow);
                    })
                },
                error: (err) => {
                    console.log(err)
                }
            });
        });

        $("#product-search .btn-search").click(()=>{
            var myUrl = "/product/get-all";
            var productID = $("#product-search > input[type=text]").val();

            if (productID != "") {
                myUrl = "/product/" + productID;
            }
            console.log(myUrl);

            $.ajax({
                url: myUrl,
                type: "GET",
                contentType: "application/json",
                success: (data, status, response) => {
                    $("#list-product-management .data-details").html("");
                    $(data).each((index, dataObject)=> {
                        var myRow = '<div class="row">' +
                            '<div class="col">' +
                            '<a href="">' +
                            (dataObject.id == null ? "" : dataObject.id) +
                            '</a>' +
                            '</div>' +
                            '<div class="col">' + (dataObject.name == null ? "" : dataObject.name) + '</div>' +
                            '<div class="col">' + (dataObject.price == null ? "" : dataObject.price) + '</div>' +
                            '<div class="col">' + (dataObject.userId == null ? "" : dataObject.userId) + '</div>' +
                            '<div class="col">' + (dataObject.description == null ? "" : dataObject.description) + '</div>' +
                            '<div class="col">' + (dataObject.creationTime == null ? "" : dataObject.creationTime) + '</div>' +
                            '<div class="col">' + (dataObject.lastUpdate == null ? "" : dataObject.lastUpdate) + '</div>' +
                        '</div>'

                        console.log(myRow);
                        $("#list-product-management .data-details").append(myRow);
                    })
                },
                error: (err) => {
                    console.log(err)
                }
            });
        });

        $("#category-search .btn-search").click(()=>{
            var myUrl = "/category/get-all";
            var categoryID = $("#category-search > input[type=text]").val();

            if (categoryID != "") {
                myUrl = "/category/" + categoryID;
            }
            console.log(myUrl);

            $.ajax({
                url: myUrl,
                type: "GET",
                contentType: "application/json",
                success: (data, status, response) => {
                    $("#list-category-management .data-details").html("");

                    $(data).each((index, dataObject)=> {
                        var myRow = '<div class="row">' +
                            '<div class="col">' +
                            '<a href="">' +
                            (dataObject.categoryId == null ? "" : dataObject.categoryId) +
                            '</a>' +
                            '</div>' +
                            '<div class="col">' + (dataObject.name == null ? "" : dataObject.name) + '</div>' +
                            '<div class="col">' + (dataObject.userId == null ? "" : dataObject.userId) + '</div>' +
                            '<div class="col">' + (dataObject.creationTime == null ? "" : dataObject.creationTime) + '</div>' +
                            '<div class="col">' + (dataObject.lastUpdate == null ? "" : dataObject.lastUpdate) + '</div>' +
                            ''
                        '</div>'

                        console.log(myRow);
                        $("#list-category-management .data-details").append(myRow);
                    })
                },
                error: (err) => {
                    console.log(err)
                }
            });
        });



        let checkResponse = function(data) {
            if (data == "yes") {
                $("#modal-add-user").css("display", "none");
                alert("Add user successful!")
            } else {
                $("#modal-add-user .error").html("Error add user");
                $("#modal-add-user .error").css({
                    "font-weight": "bold",
                    "color": "red"
                });
            }
        };
    }



    
    if (location == "/admin" || location == "/accountinfo") {
        console.log("this page is admin page")
        $.ajax({
            url: "/roles",
            type: "GET",
            contentType: "application/json",
            success: (data, status, response) => {
                console.log("data: ", data);
            },
            error: (err) => {console.log(err)}
        })

        $("#updatepassword-form button").click(()=> {
            console.log("udpate password form clicked");
            const data = {};
            data['old-pw'] = $("#old-pw").val();
            data['new-pw'] = $("#new-pw").val();
            const jsonData = JSON.stringify(data);

            console.log(data);

            $.ajax({
                url: "/update-password",
                type: "POST",
                contentType: "application/json",
                data: jsonData,
                success: (data, status, response) => {


                    if (data['data'] != "true") {
                        //if error, display it
                        console.log("password unmatch: " + data['data']) 
                        $("#updatepassword-form .error").html("Password not match");
                        $("#updatepassword-form .error").css("color","green");
                    } else {
                        $("#updatepassword-form .error").html("Success");
                        $("#updatepassword-form .error").css("color","green");
                    }

                    //make empty of inputs 
                    $("#old-pw").val("");
                    $("#new-pw").val("");
                },
                error: (err) => {console.log(err)}
            });
        });

        $("#userinfo-form button").click(()=> {
            console.log("udpate password form clicked");
            const data = {};
            data['username'] = $("#u-username").val();
            data['email'] = $("#u-email").val();
            data['phoneNumber'] = $("#u-phoneNumber").val();
            data['fullName'] = $("#u-fullName").val();
            data['enabled'] = $("#userinfo-form input[name=enabled]").val();

            const jsonData = JSON.stringify(data);
            console.log(data);

            $.ajax({
                url: "/updates",
                type: "POST",
                contentType: "application/json",
                data: jsonData,
                success: (data, status, response) => {
                    console.log(data);
                },
                error: (err) => {console.log(err)}
            });
        });

    }

});