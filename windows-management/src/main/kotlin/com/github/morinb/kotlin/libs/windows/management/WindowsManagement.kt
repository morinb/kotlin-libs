/*
 * Copyright (c) 2022. baptiste
 *
 * This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.morinb.kotlin.libs.windows.management

import java.awt.Dimension
import java.awt.Point
import kotlin.math.sqrt

/**
 * Windows management object that defines how to tile and cascade windows of a IWindowManager.
 *
 */
object WindowsManagement {

    /**
     * Defines the constant offset for the cascading
     */
    private const val CASCADING_OFFSET = 24

    /**
     * Gets the windows of the window manager, gets the desktop pane from the window manager and
     * defines the current x and y position.
     * For each frame in the frames list
     * if the frame is icon, continue
     * pack the frame
     * if the new x position is greater than the desktop pane width, set the x position to 0
     * if the new y position is greater than the desktop pane height, set the y position to 0
     * set the frame location
     * set the selected window in the windowManager
     * update the x and y position with the offset
     */
    fun cascade(windowManager: IWindowsManager) {


        val frames = windowManager.getWindows()
        val desktopPane = windowManager.getDesktopPane()
        var x = 0
        var y = 0
        for (frame in frames) {
            if (frame.isIcon) {
                continue
            }
            frame.pack()
            if (x + frame.width > desktopPane.width) {
                x = 0
            }
            if (y + frame.height > desktopPane.height) {
                y = 0
            }
            frame.location = Point(x, y)
            windowManager.setSelectedWindow(frame)
            x += CASCADING_OFFSET
            y += CASCADING_OFFSET
        }
    }

    /**
     * Tile the windows of a IWindowManager.
     *
     * gets the windows of the window manager.
     * gets the desktop pane from the window manager.
     * count the number of frames in the frames list.
     * compute the number of columns and rows and extra columns.
     *
     * defines the current column and row position.
     *
     * compute the default width and height of each frame based on the desktop pane dimensions and the number of rows and columns
     *
     * For each window in the frames list
     * if the window is icon, continue
     * if the window is maximized, un-maximize the window
     *
     * sets the window location based on the column and row position
     * sets the window size based on the default width and height
     *
     * next row
     *
     * if the current row is the last row, set the current row to 0 and increment the column position
     * if the curent column is the number of columns minus the number of extra columns, add a row
     * modify the height to take in account the new number of rows
     */
    fun tile(windowManager: IWindowsManager) {
        val frames = windowManager.getWindows()
        val desktopPane = windowManager.getDesktopPane()

        val numberOfFrames = frames.size
        var numberOfRows = sqrt(numberOfFrames.toDouble()).toInt()
        val numberOfColumns = numberOfFrames / numberOfRows
        val numberOfExtraColumns = numberOfFrames % numberOfRows

        var col = 0
        var row = 0

        val width = desktopPane.width / numberOfColumns
        var height = desktopPane.height / numberOfRows

        for (frame in frames) {
            if (frame.isIcon) {
                continue
            }
            if (frame.isMaximum) {
                frame.isMaximum = false
            }
            frame.location = Point(col * width, row * height)
            frame.size = Dimension(width, height)
            row++
            if (row == numberOfRows) {
                row = 0
                col++
                if (col == numberOfColumns - numberOfExtraColumns) {
                    numberOfRows++
                    height = desktopPane.height / numberOfRows
                }
            }
        }
    }

}