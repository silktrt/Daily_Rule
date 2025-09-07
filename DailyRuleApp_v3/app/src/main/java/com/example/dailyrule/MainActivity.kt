package com.example.dailyrule

// (imports same as before)
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                DailyRuleApp()
            }
        }
    }
}

// --- (UI, Poem, ViewModel, etc remain identical to v2) ---
// For brevity, we reuse v2 logic but replace localPoems content.

// ...[snipped repeated code: DailyRuleApp(), PoemViewModel, etc]...

// Final static data
private val jrrtQuotes = listOf(
    "Not all those who wander are lost.",
    "Little by little, one travels far.",
    "Faithless is he that says farewell when the road darkens.",
    "All we have to decide is what to do with the time that is given us.",
    "Courage is found in unlikely places.",
    "Even the smallest person can change the course of the future.",
    "The world is indeed full of peril and in it there are many dark places; but still there is much that is fair.",
    "I will not say: do not weep; for not all tears are an evil.",
    "There is some good in this world, and it's worth fighting for.",
    "Moonlight drowns out all but the brightest stars."
)

// --- Curated Poems ---
private val localPoems = listOf(
    Poem(
        "Death Be Not Proud",
        "John Donne",
        listOf(
            "Death, be not proud, though some have called thee",
            "Mighty and dreadful, for thou art not so;",
            "For those whom thou think'st thou dost overthrow",
            "Die not, poor Death, nor yet canst thou kill me.",
            "From rest and sleep, which but thy pictures be,",
            "Much pleasure; then from thee much more must flow,",
            "And soonest our best men with thee do go,",
            "Rest of their bones, and soul's delivery.",
            "Thou art slave to fate, chance, kings, and desperate men,",
            "And dost with poison, war, and sickness dwell,",
            "And poppy or charms can make us sleep as well",
            "And better than thy stroke; why swell'st thou then?",
            "One short sleep past, we wake eternally,",
            "And death shall be no more; Death, thou shalt die."
        )
    ),
    Poem(
        "Sonnet 18",
        "William Shakespeare",
        listOf(
            "Shall I compare thee to a summer’s day?",
            "Thou art more lovely and more temperate:",
            "Rough winds do shake the darling buds of May,",
            "And summer’s lease hath all too short a date;",
            "Sometime too hot the eye of heaven shines,",
            "And often is his gold complexion dimmed;",
            "And every fair from fair sometime declines,",
            "By chance, or nature’s changing course, untrimmed;",
            "But thy eternal summer shall not fade,",
            "Nor lose possession of that fair thou ow’st;",
            "Nor shall Death brag thou wanderest in his shade,",
            "When in eternal lines to time thou grow’st;",
            "So long as men can breathe, or eyes can see,",
            "So long lives this, and this gives life to thee."
        )
    ),
    Poem(
        "The World Is Too Much With Us",
        "William Wordsworth",
        listOf(
            "The world is too much with us; late and soon,",
            "Getting and spending, we lay waste our powers;—",
            "Little we see in Nature that is ours;",
            "We have given our hearts away, a sordid boon!",
            "This Sea that bares her bosom to the moon;",
            "The winds that will be howling at all hours,",
            "And are up-gathered now like sleeping flowers;",
            "For this, for everything, we are out of tune;",
            "It moves us not.—Great God! I’d rather be",
            "A Pagan suckled in a creed outworn;",
            "So might I, standing on this pleasant lea,",
            "Have glimpses that would make me less forlorn;",
            "Have sight of Proteus rising from the sea;",
            "Or hear old Triton blow his wreathed horn."
        )
    ),
    Poem(
        "A Red, Red Rose",
        "Robert Burns",
        listOf(
            "O my Luve is like a red, red rose",
            "That’s newly sprung in June;",
            "O my Luve is like the melody",
            "That’s sweetly played in tune.",
            "So fair art thou, my bonnie lass,",
            "So deep in luve am I;",
            "And I will luve thee still, my dear,",
            "Till a’ the seas gang dry."
        )
    ),
    Poem(
        "Remember",
        "Christina Rossetti",
        listOf(
            "Remember me when I am gone away,",
            "Gone far away into the silent land;",
            "When you can no more hold me by the hand,",
            "Nor I half turn to go yet turning stay.",
            "Remember me when no more day by day",
            "You tell me of our future that you planned:",
            "Only remember me; you understand",
            "It will be late to counsel then or pray."
        )
    ),
    Poem(
        "On His Blindness",
        "John Milton",
        listOf(
            "When I consider how my light is spent,",
            "Ere half my days, in this dark world and wide,",
            "And that one Talent which is death to hide",
            "Lodg'd with me useless, though my Soul more bent",
            "To serve therewith my Maker, and present",
            "My true account, lest he returning chide;",
            "“Doth God exact day-labour, light denied?”",
            "I fondly ask. But Patience, to prevent",
            "That murmur, soon replies, “God doth not need",
            "Either man’s work or his own gifts; who best",
            "Bear his mild yoke, they serve him best. His state",
            "Is Kingly. Thousands at his bidding speed",
            "And post o’er Land and Ocean without rest:",
            "They also serve who only stand and wait.”"
        )
    ),
    // Tolkien selections
    Poem(
        "All that is gold does not glitter",
        "J. R. R. Tolkien",
        listOf(
            "All that is gold does not glitter,",
            "Not all those who wander are lost;",
            "The old that is strong does not wither,",
            "Deep roots are not reached by the frost.",
            "From the ashes a fire shall be woken,",
            "A light from the shadows shall spring;",
            "Renewed shall be blade that was broken,",
            "The crownless again shall be king."
        )
    ),
    Poem(
        "The Road Goes Ever On",
        "J. R. R. Tolkien",
        listOf(
            "The Road goes ever on and on",
            "Down from the door where it began.",
            "Now far ahead the Road has gone,",
            "And I must follow, if I can,",
            "Pursuing it with eager feet,",
            "Until it joins some larger way",
            "Where many paths and errands meet.",
            "And whither then? I cannot say."
        )
    ),
    Poem(
        "I Sit Beside the Fire and Think",
        "J. R. R. Tolkien",
        listOf(
            "I sit beside the fire and think",
            "Of all that I have seen,",
            "Of meadow-flowers and butterflies",
            "In summers that have been;",
            "Of yellow leaves and gossamer",
            "In autumns that there were,",
            "With morning mist and silver sun",
            "And wind upon my hair."
        )
    ),
    Poem(
        "In Western Lands",
        "J. R. R. Tolkien",
        listOf(
            "In western lands beneath the Sun",
            "The flowers may rise in Spring,",
            "The trees may bud, the waters run,",
            "The merry finches sing.",
            "Or there maybe 'tis cloudless night",
            "And swaying branches bear",
            "The Elven-stars as jewels white",
            "Amid their branching hair."
        )
    )
)