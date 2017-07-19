$(function() {
    $('.treeview li').click(function(event) {
      $('.treeview li').removeClass('active');
      $(this).addClass('active');
    });
});