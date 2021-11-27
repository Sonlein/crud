import * as utils from "./utils.js"

$(document).ready(async function () {

    await updateUsersTable()

    $(".table #editBtn").bind("click", async function() {
        let patchedUser = await fetch("/api/users/" + this.value)
            .then(response => response.json())
            .then(user => {
                return user
            })
        let select = $("#editRoles")
        select.empty()
        $("#editId").val(patchedUser.id)
        $("#editName").val(patchedUser.name)
        $("#editPassword").val(patchedUser.password)
        let roles = await fetch("/api/roles")
            .then(response => response.json())
            .then(rolesList => {
                return rolesList
            })
        for (let role of roles) {
            let option = $("<option/>")
            option.val(role.id)
            option.text(role.role)
            for (let userRole of patchedUser.roles) {
                if (role.id === userRole.id) {
                    option.attr("selected", true)
                    break
                }
            }
            select.append(option)
        }
        $("#editModal").modal("show")
    })

    $(".table #deleteBtn").bind("click", async function() {
        let deletedUser = await fetch("/api/users/" + this.value)
            .then(response => response.json())
            .then(user => {
                return user
            })
        let select = $("#deleteRoles")
        select.empty()
        $("#deleteId").val(deletedUser.id)
        $("#deleteName").val(deletedUser.name)
        $("#deletePassword").val(deletedUser.password)
        for (let role of deletedUser.roles) {
            let option = $("<option/>")
            option.text(role.role)
            select.append(option)
        }
        $("#deleteModal").modal("show")
    })

    $("#submitEditBtn").bind("click", async function() {
        let patchedUser = {
            name: $("#editName").val(),
            password: $("#editPassword").val(),
            roles: []
        }
        for (let option of $("#editRoles").children()) {
            if (option.selected) {
                let patchedRoleId = option.value
                await fetch("/api/roles/" + patchedRoleId)
                    .then(response => response.json())
                    .then(role => {
                        patchedUser.roles.push(role)
                    })
            }
        }
        let patchedUserId = $("#editId").val()
        await fetch("/api/users/" + patchedUserId, {
            method: "PATCH",
            body: JSON.stringify(patchedUser),
            headers: {
                "Content-Type": "application/json"
            }
        })
        await updateUsersTable()
        if (Number(patchedUserId) === await utils.getAuthUserId()) {
            await utils.updateUserInfo()
        }
    })

    $("#submitDeleteBtn").bind("click", async function() {
        let deletedUserId = $("#deleteId").val()
        if (Number(deletedUserId) === await utils.getAuthUserId()) {
            window.location = "/logout"
        }
        await fetch("/api/users/" + deletedUserId, {
            method: "DELETE"
        })
        await updateUsersTable()
    })

    $("#submitNewBtn").bind("click", async function() {
        let createdUser = {
            name: $("#newName").val(),
            password: $("#newPassword").val(),
            roles: []
        }
        $("#newName").val("")
        $("#newPassword").val("")
        for (let option of $("#newRoles").children()) {
            if (option.selected) {
                await fetch("/api/roles/" + option.value)
                    .then(response => response.json())
                    .then(role => {
                        createdUser.roles.push(role)
                    })
                option.selected = false
            }
        }
        await fetch("/api/users", {
            method: "POST",
            body: JSON.stringify(createdUser),
            headers: {
                "Content-Type": "application/json"
            }
        })
        await updateUsersTable()
    })
})

async function updateUsersTable() {
    let body = $(".table #usersTableBody")
    body.empty()
    let users = await fetch("/api/users")
        .then(response => response.json())
        .then(users => {
            return users
        })
    for (let user of users) {
        let tr = $("<tr/>")
        let th = $("<th/>")
        th.text(user.id)
        tr.append(th)
        let tdName = $("<td/>")
        tdName.text(user.name)
        tr.append(tdName)
        let tdPassword = $("<td/>")
        tdPassword.text(user.password)
        tr.append(tdPassword)
        body.append(tr)
        let tdRoles = $("<td/>")
        let roles = ""
        for (let role of user.roles) {
            roles += `${role.role} `
        }
        tdRoles.text(roles)
        tr.append(tdRoles)
        let tdEdit = $("<td/>")
        let editBtn =$("<button id='editBtn' class='btn btn-info' type='button'>")
        editBtn.text("Edit")
        editBtn.val(user.id)
        tdEdit.append(editBtn)
        tr.append(tdEdit)
        let tdDelete = $("<td/>")
        let deleteBtn =$("<button id='deleteBtn' class='btn btn-danger' type='button'>")
        deleteBtn.text("Delete")
        deleteBtn.val(user.id)
        tdDelete.append(deleteBtn)
        tr.append(tdDelete)
    }
}