$(function () {
    /* Custom filtering function which will search data in column four between two values */
    $.fn.dataTable.ext.search.push(
        function (settings, data, dataIndex) {
            var minDate = $('#minDatePicker').val();
            var maxDate = $('#maxDatePicker').val();
            var date = data[0]; // use data for the date column
            return dateSorter(minDate, date) < 1 && dateSorter(maxDate, date) > -1;
        }
    );

    $.fn.dataTable.ext.search.push(
        function (settings, data, dataIndex) {
            var minTime = $('#minTimePicker').val();
            var maxTime = $('#maxTimePicker').val();
            var time = data[1]; // use data for the time column
            return timeSorter(minTime, time) < 1 && timeSorter(maxTime, time) > -1;
        }
    );

    $.fn.dataTable.moment('DD.MM.YYYY');
    var timeColumn; // this needs to be initalized here due to TimePicker's special implementation of the change event
    // This section is heavily made after DataTables examples from their website
    var table = $('#visitsTable').dataTable({
        "dom": 'lrtip', // this takes out the global search bar
        "language": {
            "zeroRecords": "",
            "emptyTable": ""
        },
        order: [[0, 'asc'], [1, 'asc']],
        paging: false,
        info: false,
        "columnDefs": [
            {
                "targets": [-1, -2],
                "orderable": false
            }
        ],
        initComplete: function () {
            var dentistColumn = this.api().column(2);
            var select = $('<select class="form-control"><option value=""></option></select>')
                .appendTo($('#dentistFilter'))
                .on('change', function () {
                    dentistColumn.search($(this).val()).draw();
                });

            dentistColumn.data().unique().sort().each(function (d, j) {
                select.append('<option value="' + d + '">' + d + '</option>')
            });

            var dateColumn = this.api().column(0);
            $("#minDatePicker, #maxDatePicker").change(function () {
                dateColumn.search("").draw();
            });

            timeColumn = this.api().column(1);
        }
    });

    $("#minDatePicker, #maxDatePicker").datepicker();
    $("#minTimePicker, #maxTimePicker").timepicker(
        {
            timeFormat: 'HH:mm',
            minTime: '8',
            maxTime: '18',
            dynamic: false,
            dropdown: true,
            scrollbar: true,
            change: function(time) {
                timeColumn.search("").draw();
            }
        }
    );
});