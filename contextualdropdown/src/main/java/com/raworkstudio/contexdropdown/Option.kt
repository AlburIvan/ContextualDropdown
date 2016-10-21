package com.raworkstudio.contexdropdown

/**
 * Created by Ivan Alburquerque on 9/8/2016
 */
class Option {

    var icon: Int = 0

    var title: String

    var subtitle: String


    constructor(title: String, subtitle: String) {
        this.title = title
        this.subtitle = subtitle
    }


    constructor(title: String, subtitle: String, iconId: Int) {
        this.title = title
        this.subtitle = subtitle
        this.icon = iconId

    }

}
