package com.donoroncall.server.models

/**
  * Created by vishnu on 14/4/16.
  */

/**
  *
  * @param id
  * @param userId
  * @param requestId
  * @param status
  * -2 => Deleted Record
  * -1 => Request Completed
  * 0 => Recipient Canceled
  * 1 => Pending
  * 2 => Donor Accepted
  * 3 => Donor Canceled
  * 4 => Successfully Completed
  */
class DonationRecord(
                      id: Long = 0,
                      userId: Long,
                      requestId: Long,
                      status: Int = 0
                    ) {


}
