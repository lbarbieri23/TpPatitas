<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!--<footer class="main-footer" style="position: absolute; bottom: 0px; width:100%">
        <div class="float-right d-none d-sm-inline">
            Rescate de patitas
        </div>
         Default to the left
        <strong>Copyright &copy; 2014-2021 <a href="https://adminlte.io">AdminLTE.io</a>.</strong> All rights reserved. Atrevete a plagiar, y te las verás con nuestros mastines.
    </footer>-->
</div>


<c:if test="${command.isToastShow}">
    <script>
        var Toast = Swal.mixin({
            toast: true,
            position: 'top',
            showConfirmButton: false,
            timer: 2000,
            timerProgressBar: true,
            onOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        });

        Toast.fire({icon: 'success', title: '${command.toastMessage}' });

    </script>
</c:if>


<script>

    $(document).ready(function() {

        $('.select2bs4').select2({
            theme: 'bootstrap4'
        });

        $('[data-mask]').inputmask();

        $('.date-picker').daterangepicker({
            singleDatePicker: true,
            autoApply: true,
            autoClose: true,
            locale: {
                format: 'DD/MM/YYYY'
            }
        });

        $('.date-time-picker').daterangepicker({
            singleDatePicker: true,
            timePicker: true,
            timePickerIncrement: 5,
            autoApply: true,
            autoClose: true,
            timePicker24Hour: true,
            locale: {
                format: 'DD/MM/YYYY HH:mm:ss'
            }
        });

        $(".allow-decimal").on("keypress keyup blur", function(event) {
            $(this).val($(this).val().replace(/[^0-9\.]/g, ''));
            if ((event.which != 46 || $(this).val().indexOf('.') != -1)
                && (event.which < 48 || event.which > 57)) {
                event.preventDefault();
            }
        });

        $(".allow-integer").on("keypress keyup blur", function(event) {
            $(this).val($(this).val().replace(/[^\d].+/, ""));
            if ((event.which < 48 || event.which > 57)) {
                event.preventDefault();
            }
        });

        $(".allow-integer-negative").on("keypress keyup blur", function(event) {
            $(this).val($(this).val().replace(/[-?][^\d].+/, ""));
            if ((event.which < 48 || event.which > 57 || event.which == 45)) {
                event.preventDefault();
            }
        });

        $("div[class*='color-picker-']").colorpicker();
        $("div[class*='color-picker-']").on('colorpickerChange', function(event) {
            $(this).find('.fa-square').css('color', event.color.toString());
        });

        $('.color-picker').colorpicker();
        $('.color-picker').on('colorpickerChange', function(event) {
            $('.color-picker .fa-square').css('color', event.color.toString());
        });

        $(document).ajaxError(function globalUnauthorizedAjaxHandler(event, xhr, ajaxOptions, thrownError) {
            if(xhr.status == 403) {
                window.location = '<c:out value="${pageContext.request.contextPath}"/>/logout.html';
            }
        });

        $("input[data-bootstrap-switch]").each(function() {
            $(this).bootstrapSwitch();
        });

        bsCustomFileInput.init();

    });


</script>

</body>
</html>

