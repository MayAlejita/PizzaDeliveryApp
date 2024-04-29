$(document).ready(function () {

    $('.update-orderLine').on('click', 'button[data-update-orderLine-url]', function () {
        let url = $(this).data('update-orderLine-url');

        // adding the row index, needed when deleting a orderLine
        let formData = $('form').serializeArray();
        let param = {};
        param["name"] = $(this).attr('name');
        param["value"] = $(this).val();
        formData.push(param);

        // updating the contact section
        $('#tblOrderLine').load(url, formData);
    });
});