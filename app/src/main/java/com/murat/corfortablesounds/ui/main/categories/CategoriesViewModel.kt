package com.murat.corfortablesounds.ui.main.categories

import android.app.Application
import androidx.databinding.ObservableField
import com.murat.corfortablesounds.App
import com.murat.corfortablesounds.core.BaseViewModel

class CategoriesViewModel(app: Application) : BaseViewModel(app) {
    var backgroundImageNature =
        "https://www.theglobeandmail.com/resizer/CiTkf2JIqrKG3C4P3y0PLbf0lqk=/620x0/filters:quality(80)/arc-anglerfish-tgam-prod-tgam.s3.amazonaws.com/public/MFAAGTRQQ5EQNIJ2WEA6DMYO6Q.jpg"
    var backgroundImagePiano =
        "https://previews.123rf.com/images/corund/corund1309/corund130900177/22106111-piano-keys.jpg"
    var backgroundImageBird =
        "https://horizon-media.s3-eu-west-1.amazonaws.com/s3fs-public/field/image/brentgeese_edit.jpg"
    var toolbarTitle: ObservableField<String> = ObservableField("")

    init {
        (app as? App)?.component?.inject(this)
    }
}