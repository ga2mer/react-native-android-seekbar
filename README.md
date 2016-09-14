
# react-native-android-seekbar

## Getting started

`$ npm install react-native-android-seekbar --save`

### Mostly automatic installation

`$ react-native link react-native-android-seekbar`

### Manual installation

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.ga2mer.seekbar.RNAndroidSeekbarPackage;` to the imports at the top of the file
  - Add `new RNAndroidSeekbarPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-android-seekbar'
  	project(':react-native-android-seekbar').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-android-seekbar/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-android-seekbar')
  	```


## Usage
```javascript
import SeekBar from 'react-native-android-seekbar';
<SeekBar style={{
    width: 300
}} color={'#e62117'} progress={50} onChange={this.handleChange} onTrackingTouch={this.handleTrackingTouch} max={100} secondaryColor={'grey'} thumbColor={'#e62117'} bgColor={'rgba(0, 0, 0, 0.5)'}/>
```
