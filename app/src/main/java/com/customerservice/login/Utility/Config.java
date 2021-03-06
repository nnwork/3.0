package com.customerservice.login.Utility;

public class Config {
    public static String BASE_URL="http://192.168.1.146:8089/Webservices/";

    public static String ADD_BUILDING=BASE_URL+"insertBuilding.php";

    public static String ADD_FLAT=BASE_URL+"insertFlat.php";
    public static String ADD_Category=BASE_URL+"insertCategory.php";
    public static String ADD_HelperCategory=BASE_URL+"insertHelperCategory.php";
    public static String ADD_Month=BASE_URL+"insertMonth.php";
    public static String Add_Event=BASE_URL+"insertEvent.php";
    public static String Add_User=BASE_URL+"insertUser.php";


   // public static String READ_HelperUserComplain=BASE_URL+"GetHelperUserComplain.php";
   //public static String list_tbl_notification=BASE_URL+"disp_tbl_Notification.php";



   // public static String READ_GetUserContactinfo=BASE_URL+"getUserContactInfo.php";
    public static String ADD_tbl_Visitors=BASE_URL+"WatchmenAddVisitor.php";

    public static String READ_GetOwnerinfo=BASE_URL+"getOwnerInfo.php";


    public static String READ_GetUserinfo=BASE_URL+"getUserInfo.php";
    public static String READ_BUILDINGS=BASE_URL+"getBuildings.php";
    public static String READ_Flat=BASE_URL+"getFlat.php";
    public static String READ_UserFlat=BASE_URL+"getUserFlat.php";
    public static String READ_Watchmen_Flat=BASE_URL+"getWatchmenFlats.php";
    public static String READ_Event=BASE_URL+"getEvents.php";
    public static String READ_Category=BASE_URL+"getEventCategory.php";
    public static String READ_UserLogin=BASE_URL+"getUserLogin.php";
    public static String READ_Maintenace=BASE_URL+"getAdminMaintenance.php";
    public static String READ_Funds=BASE_URL+"getFunds.php";
    public static String READ_MonthlyAmount=BASE_URL+"getMonthAmount.php";
    public static String READ_Expences=BASE_URL+"getExpences.php";
    public static String READ_Hall=BASE_URL+"getHall.php";
    public static String READ_Hall_Booking=BASE_URL+"getHallBooking.php";
    public static String READ_Hall_BookingDetail=BASE_URL+"getHallBookingDetail.php";

    public static String READ_Helper=BASE_URL+"getHelper.php";
    public static String READ_Helper_Category=BASE_URL+"getHelperCategory.php";
    public static String READ_Suggestion=BASE_URL+"getSuggestion.php";
    public static String READ_FlatUserDetail=BASE_URL+"getFlatDetail.php";
    public static String READ_AdminUserMaintenance=BASE_URL+"GetAdminUserMaintenance.php";
    public static String READ_UserEvents=BASE_URL+"GetUserEvents.php";
    public static String READ_OwnerUserMaintenance=BASE_URL+"GetOwnerUserMaintenance.php";
    public static String READ_OwnerUserComplain=BASE_URL+"GetOwnerUserComplain.php";
    public static String READ_OwnerUserVisitor=BASE_URL+"GetOwnerUserVisitor.php";
    public static String READ_HelperUserComplain=BASE_URL+"GetHelperUserComplain.php";

    public static String READ_GetUserContactinfo=BASE_URL+"getUserContactInfo.php";



    public static String ADD_tbl_complain=BASE_URL+"insert_tbl_complain.php";
    public static String ADD_tbl_fund=BASE_URL+"insert_tbl_fund.php";
    public static String ADD_tbl_expence=BASE_URL+"insert_tbl_expense.php";
    public static String ADD_tbl_hall=BASE_URL+"insert_tbl_hall.php";
    public static String ADD_tbl_hall_booking=BASE_URL+"insert_tbl_hall_booking.php";
    public static String ADD_tbl_suggestion=BASE_URL+"insert_tbl_suggestion.php";
   // public static String ADD_tbl_Visitors=BASE_URL+"AddVisitor.php";

    public static String Disp_tbl_hall_booking=BASE_URL+"disp_tbl_hall_booking.php";
    public static String Disp_tbl_user_hall=BASE_URL+"disp_tbl_user_hall_booking.php";
    public static String Disp_tbl_user_suggestion=BASE_URL+"disp_tbl_suggestion.php";
    public static String Disp_tbl_halper_comlain=BASE_URL+"disp_help_cat_tbl_complain.php";
    public static String Disp_tbl_user_comlain=BASE_URL+"disp_user_tbl_complain.php";

    public static String list_tbl_complain=BASE_URL+"disp_tbl_complain.php";
    public static String list_tbl_visitor=BASE_URL+"disp_tbl_visitor.php";
 public static String list_tbl_admin_visitor=BASE_URL+"disp_tbl_admin_visitor.php";
    public static String list_tbl_events=BASE_URL+"disp_tbl_events.php";
    public static String list_tbl_hall=BASE_URL+"disp_tbl_hall.php";
    public static String list_tbl_owner_complain=BASE_URL+"disp_tbl_complain.php";
    public static String list_tbl_maintenance=BASE_URL+"disp_tbl_maintenance.php";
    public static String list_tbl_fund=BASE_URL+"disp_tbl_fund.php";
    public static String list_tbl_suggestions=BASE_URL+"disp_tbl_suggestion.php";
    public static String list_tbl_notification=BASE_URL+"disp_tbl_Notification.php";

    //Admin
    public static String list_tbl_admin_complain=BASE_URL+"disp_tbl_complain.php";
    public static String list_tbl_admin_tbl_complain=BASE_URL+"disp_admin_tbl_complain.php";
    public static String list_tbl_admin_complain_details=BASE_URL+"DispAdminComplainDetails.php";
    public static String list_tbl_admin_complain_status=BASE_URL+"disp_tbl_complain_status.php";
    public static String Details_Admin_Helper=BASE_URL+"GetAdminHelperDetails.php";
    public static String List_Admin_Watchmen=BASE_URL+"GetAdminWatchmenActivity.php";


    public static String UPLOAD_BUILDING_IMAGE=BASE_URL+"uploadbuildingimage.php";
    public static String UPLOAD_Hall_IMAGE=BASE_URL+"uploadhallimage.php";
    public static String UPLOAD_Complain_IMAGE=BASE_URL+"uploadcomplainimage.php";

 // global topic to receive app wide push notifications
 public static final String TOPIC_GLOBAL = "global";

 // broadcast receiver intent filters
 public static final String REGISTRATION_COMPLETE = "registrationComplete";
 public static final String PUSH_NOTIFICATION = "pushNotification";

 // id to handle the notification in the notification tray
 public static final int NOTIFICATION_ID = 100;
 public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

 public static final String SHARED_PREF = "ah_firebase";

}
