const url = 'http://localhost:8080/api/admin/users/';

//All Users
async function tab() {
    await fetch(url)
        .then(res => {
            res.json()
                .then(data => {
                    console.log(data);
                    if (data.length > 0) {
                        let temp = "";
                        data.forEach((u) => {
                            temp += "<tr id='deluser'>";
                            temp += "<td id='editid'>" + u.id + "</td>";
                            temp += "<td id='editname'>" + u.name + "</td>";
                            temp += "<td id='editage'>" + u.age + "</td>";
                            temp += "<td id='editemail'>" + u.email + "</td>";
                            temp += '<td><button type="button" id ="editbutton" class="btn btn-primary" data-toggle="modal" ' +
                                'data-target="#exampleModalCenter" data-idedit="' +
                                u.id + '">Edit</button></td>';
                            temp += '<td><button type="button" id= "deletebutton" class="btn btn-danger" data-toggle="modal" ' +
                                'data-target="#userd" data-iddelete="' + u.id + '">Delete</button></td>';
                            temp += "<td>";
                            let key;
                            for (key in u.roles) {
                                temp += (u.roles[key].name).replace('ROLE_', ' ') + " ";
                            }
                            temp += "</td>";
                            temp += "</tr>";
                        })
                        document.getElementById("data").innerHTML = temp;
                    }
                })
        })
}

tab();
//User info
const url1 = 'http://localhost:8080/api/user';
let bodynew = document.getElementById("bodynew");

async function userinfo() {
    await fetch(url1)
        .then(res => {
            res.json()
                .then(data1 => {
                        console.log(data1);
                        let temp = "";
                        temp += "<tr><th>" + data1.id + "</th>" +
                            "<th>" + data1.name + "</th>" +
                            "<th>" + data1.age + "</th>" +
                            "<th>" + data1.email + "</th></tr>";
                        $("#bodynew").append(temp);
                    }
                )
        })
}

//creat user
console.log(document.getElementById('creatuser1'));

async function newUser(e) {
    e.preventDefault();
    const creatname = document.getElementById('name1');
    const creatage = document.getElementById('age1');
    const createmail = document.getElementById('email1');
    const creatpassword = document.getElementById('password1');
    const creatroles = document.getElementById('role1');
    console.log('ok');
    console.log(creatname.value);
    await fetch(url, {
        method: "POST", headers: {"Content-type": "application/json; charset=UTF-8"},
        body: JSON.stringify({
            name: creatname.value, age: creatage.value, email: createmail.value,
            password: creatpassword.value, roles: $.trim($('#role1').val())
        })
    })
        .then(response => response.json())
        .then(json => console.log(json))
        .then(() => location.reload())

}

//DELETE user
$("#userd").on("show.bs.modal", function (e) {
        let id = $(e.relatedTarget).data('iddelete')//document.getElementById("deletebutton").dataset.iddelete;
        console.log(id);
        fetch("http://localhost:8080/api/findOne/" + id)
            .then(response => response.json())
            .then(json => {
                console.log(json)
                document.getElementById("idd").value = json.id;
                document.getElementById("named").value = json.name;
                document.getElementById("aged").value = json.age;
                document.getElementById("emaild").value = json.email;
                document.getElementById("passwordd").value = json.password;
            })
    }
)

async function deleteuser(e) {
    let id = document.getElementById("idd").value;
    await fetch(url + id, {method: "DELETE"})
        .then(alert("User DELETE"))
        .then(() => location.reload())
}

//updateUser
let editid = document.getElementById('ide');
let editname = document.getElementById('namee');
let editage = document.getElementById('agee');
let editemail = document.getElementById('emaile');
let editpassword = document.getElementById('passworde');
let editroles = document.getElementById('rolee');

$('#exampleModalCenter').on('show.bs.modal', function (event) {
    let id = $(event.relatedTarget).data('idedit')
    console.log(id);
    fetch("http://localhost:8080/api/findOne/" + id)
        .then(response => response.json())
        .then(json => {
            console.log(json)
            editid.value = json.id;
            editname.value = json.name;
            editage.value = json.age;
            editemail.value = json.email;
            editpassword.value = json.password;
            editroles.value = json.roles;
        })
})

async function edituser(e) {
    let id = document.getElementById("ide").value;
    await fetch(url + id, {
        method: "PUT", headers: {"Content-type": "application/json; charset=UTF-8"},
        body: JSON.stringify({
            id: editid.value, name: editname.value, age: editage.value, email: editemail.value,
            password: editpassword.value, roles: $.trim($('#rolee').val())
        })
    })
        .then(alert("EditUser"))
        .then(() => location.reload())
}






