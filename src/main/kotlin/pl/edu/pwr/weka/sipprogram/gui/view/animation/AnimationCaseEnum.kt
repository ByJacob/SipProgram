package pl.edu.pwr.weka.sipprogram.gui.view.animation

import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 02.12.2018 19:44
 */

val resources = FX.messages

enum class AnimationCaseEnum(val animName: String, val parameters: List<AnimationCaseEnumParameters>,
                             val documentationUrl: String = "") {

    BASIC_REGISTER(resources["name1"], listOf<AnimationCaseEnumParameters>(
            AnimationCaseEnumParameters(resources["title10"], resources["description10"],
                    "", Pair(false, false)),
            AnimationCaseEnumParameters(resources["title11"], resources["description11"],
                    resources["register"], Pair(true, false), resources["example11"]),
            AnimationCaseEnumParameters(resources["title12"], resources["description12"],
                    resources["ack401"], Pair(false, true), resources["example12"]),
            AnimationCaseEnumParameters(resources["title13"], resources["description13"],
                    resources["register"], Pair(true, false), resources["example13"]),
            AnimationCaseEnumParameters(resources["title14"], resources["description14"],
                    resources["ack200"], Pair(false, true), resources["example14"])
    ),"https://tools.ietf.org/html/rfc3261#section-10.3"),
    UPDATE_CONTACT_REGISTER(resources["name2"], listOf<AnimationCaseEnumParameters>(
            AnimationCaseEnumParameters(resources["title20"], resources["description20"],
                    "", Pair(false, false)),
            AnimationCaseEnumParameters(resources["title21"], resources["description21"],
                    resources["register"], Pair(true, false), resources["example21"]),
            AnimationCaseEnumParameters(resources["title22"], resources["description22"],
                    resources["ack200"], Pair(false, true), resources["example22"])
    )),
    REQUEST_CURRENT_CONTACT(resources["name3"], listOf<AnimationCaseEnumParameters>(
            AnimationCaseEnumParameters(resources["title30"], resources["description30"],
                    "", Pair(false, false)),
            AnimationCaseEnumParameters(resources["title31"], resources["description31"],
                    resources["register"], Pair(true, false), resources["example31"]),
            AnimationCaseEnumParameters(resources["title32"], resources["description32"],
                    resources["ack200"], Pair(false, true), resources["example32"])
    )),
    CANCEL_REGISTER(resources["name4"], listOf<AnimationCaseEnumParameters>(
            AnimationCaseEnumParameters(resources["title40"], resources["description40"],
                    "", Pair(false, false)),
            AnimationCaseEnumParameters(resources["title41"], resources["description41"],
                    resources["register"], Pair(true, false), resources["example41"]),
            AnimationCaseEnumParameters(resources["title42"], resources["description42"],
                    resources["ack200"], Pair(false, true), resources["example32"])
    )),
    BAD_REGISTER(resources["name5"], listOf<AnimationCaseEnumParameters>(
            AnimationCaseEnumParameters(resources["title50"], resources["description50"],
                    "", Pair(false, false)),
            AnimationCaseEnumParameters(resources["title51"], resources["description51"],
                    resources["register"], Pair(true, false), resources["example51"]),
            AnimationCaseEnumParameters(resources["title52"], resources["description52"],
                    resources["ack401"], Pair(false, true), resources["example52"]),
            AnimationCaseEnumParameters(resources["title53"], resources["description53"],
                    resources["register"], Pair(true, false), resources["example53"]),
            AnimationCaseEnumParameters(resources["title54"], resources["description54"],
                    resources["ack401"], Pair(false, true), resources["example54"])
    )),
    SUCCESSFUL_SESSION_ESTABLISHMENT(resources["name6"], listOf<AnimationCaseEnumParameters>(
            AnimationCaseEnumParameters(resources["title60"], resources["description60"],
                    "", Pair(false, false)),
            AnimationCaseEnumParameters(resources["title61"], resources["description61"],
                    resources["invite"], Pair(true, false), resources["example61"]),
            AnimationCaseEnumParameters(resources["title62"], resources["description62"],
                    resources["ack180"], Pair(false, true), resources["example62"]),
            AnimationCaseEnumParameters(resources["title63"], resources["description63"],
                    resources["ack200"], Pair(false, true), resources["example63"]),
            AnimationCaseEnumParameters(resources["title64"], resources["description64"],
                    resources["ack"], Pair(true, false), resources["example64"]),
            AnimationCaseEnumParameters(resources["title65"], resources["description65"],
                    resources["rpt_media"], Pair(true, true), resources["example65"]),
            AnimationCaseEnumParameters(resources["title66"], resources["description66"],
                    resources["bye"], Pair(false, true), resources["example66"]),
            AnimationCaseEnumParameters(resources["title67"], resources["description67"],
                    resources["ack200"], Pair(true, false), resources["example67"])
    )),
    ;


    override fun toString(): String {
        return animName
    }

    data class AnimationCaseEnumParameters(val title: String, val description: String,
                                           val arrowName: String, val isArrowRightLeft: Pair<Boolean, Boolean>,
                                           val example: String = "")
}