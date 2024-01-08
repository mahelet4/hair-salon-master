 function submitForm() {
        var form = document.createElement("form");
        form.method = "post";
        form.action = "/booking/serviceSubmitting";
        document.body.appendChild(form);
        form.submit();
    }