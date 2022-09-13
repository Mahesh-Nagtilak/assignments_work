
  export  function AdminAuthHeader() {
    
    const admin = JSON.parse(sessionStorage.getItem('admin'));
    if (admin && admin.token) {
      return { Authorization: 'Bearer ' + admin.token };
    } else {
      return {};
    }

  }