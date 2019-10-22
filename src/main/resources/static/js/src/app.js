function dateSorter(a, b) {
    var a_segments = a.split(".");
    var b_segments = b.split(".");

    var d1 = new Date(a_segments[2], a_segments[1] - 1, a_segments[0]);
    var d2 = new Date(b_segments[2], b_segments[1] - 1, b_segments[0]);

    if (d1 < d2) return -1;
    if (d1 > d2) return 1;
    return 0;
}

function timeSorter(a, b) {
    var a_segments = a.split(":");
    var b_segments = b.split(":");

    var d1 = new Date();
    var d2 = new Date();

    d1.setHours(a_segments[0], a_segments[1], 0);
    d2.setHours(b_segments[0], b_segments[1], 0);

    if (d1 < d2) return -1;
    if (d1 > d2) return 1;
    return 0;
}