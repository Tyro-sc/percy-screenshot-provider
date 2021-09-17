/**
 * Copyright © ${project.inceptionYear} Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sc.tyro.provider

import io.percy.selenium.Percy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static org.mockito.Mockito.*
import static sc.tyro.core.Config.screenshotProvider
import static sc.tyro.core.Tyro.takeScreenshot

@DisplayName("Screenshot Tests")
class PercyProviderTest {
    Percy percy = spy(new Percy(null))

    @BeforeEach
    void before() {
        screenshotProvider = new PercyProvider(percy)
        reset(percy)
    }

    @Test
    @DisplayName("Should take window screenshot with percy")
    void percyScreenshot() {
        takeScreenshot('percy')

        verify(percy, times(1)).snapshot('percy')
    }

    @Test
    @DisplayName("Should take window screenshot with percy specific config")
    void percyConfigScreenshot() {
        PercyProvider provider = screenshotProvider as PercyProvider
        provider.widths = List.of(1, 2, 3)
        provider.minHeight = 100
        provider.enableJavaScript = true
        provider.percyCSS = 'css'

        takeScreenshot('percy')

        verify(percy, times(1)).snapshot('percy', provider.widths,
                provider.minHeight, provider.enableJavaScript, provider.percyCSS)
    }
}
