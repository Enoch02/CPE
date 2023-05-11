package com.cpe.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.cpe.data.models.Course

@Composable
fun ScheduleItem(modifier: Modifier, course: Course) {
    ElevatedCard(
        modifier = modifier.padding(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        content = {
            ConstraintLayout(modifier = modifier) {
                val (circle, courseInfoLayout, spacer, timeLayout) = createRefs()

                Box(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .fillMaxHeight()
                        .constrainAs(circle) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(courseInfoLayout.start)
                        }
                )

                Column(
                    modifier = Modifier
                        .constrainAs(courseInfoLayout) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(circle.end, 12.dp)
                            end.linkTo(spacer.start)
                            height = Dimension.fillToConstraints
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    content = {
                        Text(
                            text = course.courseCode,
                            fontSize = MaterialTheme.typography.labelLarge.fontSize,
                            fontWeight = MaterialTheme.typography.labelLarge.fontWeight,
                            softWrap = true,
                            maxLines = 1,
                        )
                        Text(
                            text = course.lecturer,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            softWrap = true,
                            maxLines = 1,
                        )
                    }
                )

                Spacer(
                    modifier = Modifier.constrainAs(spacer) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(courseInfoLayout.end)
                        end.linkTo(timeLayout.start)
                        width = Dimension.fillToConstraints
                    }
                )

                Column(
                    modifier = Modifier
                        .constrainAs(timeLayout) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(spacer.end)
                            end.linkTo(parent.end, margin = 24.dp)
                            height = Dimension.fillToConstraints
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        Text(
                            text = "Time",
                            fontSize = MaterialTheme.typography.labelLarge.fontSize,
                            fontWeight = FontWeight.Bold,
                            softWrap = true,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                        )
                        Text(
                            text = "${course.startTime} - ${course.endTime}",
                            maxLines = 1,
                            softWrap = true,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                )
            }
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Preview() {
    Column(modifier = Modifier) {
        ScheduleItem(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
            course = Course(
                courseTitle = "Demo Course",
                courseCode = "DEM 101",
                dayOfWeek = 1,
                startTime = "8:00 am",
                endTime = "9:00 am",
                lecturer = "Engineer Jagun",
            )
        )
    }
}