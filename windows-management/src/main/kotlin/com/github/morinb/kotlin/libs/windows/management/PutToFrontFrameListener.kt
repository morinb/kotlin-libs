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

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JInternalFrame

/**
 * Listener that put a JInternalFrame in front of the desktop with the help of a IWindowManager
 */
class PutToFrontFrameListener(
    /**
     * the JInternalFrame to put in front
     */
    private val frame: JInternalFrame,
    /**
     * The window manager
     */
    private val windowManager: IWindowsManager
) : ActionListener {
    /**
     * If the frame is iconified, try to deiconify it and use the windows manager to set the selected window to this frame.
     * Then put the frame in front of the desktop.
     *
     * @param e the action event
     */
    override fun actionPerformed(e: ActionEvent?) {
        if (frame.isIcon) {
            frame.isIcon = false
        }
        windowManager.setSelectedWindow(frame)
        frame.toFront()
    }
}