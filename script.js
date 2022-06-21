function display(data) {
    let content = `<tr>
                        <th>Title</th>
                        <th>Content</th>
                        <th>Time</th>
                        <th>Likes</th>
                    </tr>`;
    console.log(data)
    for (let i = 0; i < data.length; i++) {
        content += getPost(data[i]);
    }
    document.getElementById("display1").innerHTML = content;
}
function getPost(post) {
    return `<tr>
            <td>${post.title}</td>
            <td>${post.content}</td>
            <td>${post.createAt}</td>
            <td>${post.likes}</td>
        </tr>`
}

//hiển thị tất cả sản phẩm
function findAllPost(page) {
    if (!page) {
        page = 0;
    }
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/posts?page=" + page,
        success: function (data) {
            display(data.content)
        }, error: function (error) {
            console.log(error);
        }
    })
}
let postPage = 0;

function loadMore(){
    postPage = postPage + 1;
    findAllPost(postPage);
}

function quayLai(){
    postPage = postPage -1;
    findAllPost(postPage);
}
findAllPost();

function findAllByTitleContaining() {
    let name = document.getElementById("title").value;
    let from = document.getElementById("dateFrom").value;
    let to = document.getElementById("dateTo").value;
    $.ajax({

        type: "GET",
        url: "http://localhost:8080/posts/search?title=" + name + "&dateFrom=" +from +"&dateTo="+to,
        success: function (data) {
            console.log("vao day k" + data)
            display(data)
        }, error: function (error) {
            console.log(error);
        }
    });
}

function searchByCreateAtOrderBy() {
    let from = document.getElementById("dateFrom1").value;
    let to = document.getElementById("dateTo1").value;
    $.ajax({

        type: "GET",
        url: "http://localhost:8080/posts/searchByCreateAtOrderBy?dateFrom=" +from +"&dateTo="+to,
        success: function (data) {
            console.log("vao day k" + data)
            display(data)
        }, error: function (error) {
            console.log(error);
        }
    });
}

function findByTitleAndCreateAtOrderByCreateAt() {
    let name = document.getElementById("title2").value;
    let from = document.getElementById("dateFrom2").value;
    let to = document.getElementById("dateTo2").value;
    $.ajax({

        type: "GET",
        url: "http://localhost:8080/posts/findByTitleAndCreateAtOrderByCreateAt?title=" + name + "&dateFrom=" +from +"&dateTo="+to,
        success: function (data) {
            console.log("vao day k" + data)
            display(data)
        }, error: function (error) {
            console.log(error);
        }
    });
}


