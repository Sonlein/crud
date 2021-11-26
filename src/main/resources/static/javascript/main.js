$(document).ready(function() {
    $(".table #editBtn").on("click", function(event) {
        event.preventDefault()
        let href = $(this).attr("href")
        let select = document.getElementById("editRoles")
        removeChildren(select)
        jQuery.get(href, function(user) {
            $("#editId").val(user.id)
            $("#editName").val(user.name)
            $("#editPassword").val(user.password)
            for (let role of roles) {
                let option = document.createElement("option")
                option.value = role.id
                option.text = role.role
                for (let userRole of user["roles"]) {
                    if (role.id === userRole.id) {
                        option.selected = true
                        break
                    }
                }
                select.appendChild(option)
            }
        })
        $("#editModal").modal("show")
    })

    $(".table #deleteBtn").on("click", function(event) {
        event.preventDefault()
        let href = $(this).attr("href")
        let select = document.getElementById("deleteRoles")
        removeChildren(select)
        jQuery.get(href, function(user) {
            $("#deleteId").val(user.id)
            $("#deleteName").val(user.name)
            $("#deletePassword").val(user.password)
            for (let role of user["roles"]) {
                let option = document.createElement("option")
                option.text = role.role
                select.appendChild(option)
            }
        })
        $("#deleteModal").modal("show")
    })
})

function removeChildren(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}