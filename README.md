# Best Of LastFM

This repository contains a sample app that shows you popular singers and their albums using LastFM official API.

## Screens

### Main screen

Here you can see top artists sorted by name. Additionaly, you can specify source country.

<img width="400" src="https://github.com/kllaas/BestOfLastFM/blob/images/screenshots/Screenshot_1510528376.png ">

### Artists detail screen

Artist`s albums displayed in this screen.

<img width="400" src="https://github.com/kllaas/BestOfLastFM/blob/images/screenshots/Screenshot_1510528501.png ">

### Album`s details dialog

Large picture of album and some information about it are displayed here.

<img width="400" src="https://github.com/kllaas/BestOfLastFM/blob/images/screenshots/Screenshot_1510528542.png">

## Architecture

This app implements MVP architecture using Dagger2, Room, RxJava, Retrofit 2 and OkHTTP libraries.

## Structure

#### The app has following packages:
1. **data**: It contains all the data accessing and manipulating components.
2. **di**: Dependency providing classes using Dagger2.
3. **ui**: View classes along with their corresponding Presenters.
4. **utils**: Utility classes.

## License

MIT License

Copyright (c) 2017 Alexey Klimchuk
```
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
