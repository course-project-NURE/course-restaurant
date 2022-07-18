class MenuItemService {

    basicUrl = "http://localhost:8080/menu-item";

    retrieveAllMenuItems() {
        return fetch(this.basicUrl)
    }

    retrieveMenuItemById(id) {
        return fetch(this.basicUrl + '/' + id)
    }

    deleteMenuItemById(id){
        return fetch(this.basicUrl + '/' + id, {
            method: 'DELETE',
            headers:{"Content-Type":"application/json"}
        })
    }

    saveMenuItem(menuItem){
        const url = this.basicUrl + (menuItem.id ? '/' + menuItem.id : '')
        return fetch(url, {
            method: (menuItem.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(menuItem),
        })
    }
}

export default new MenuItemService()
