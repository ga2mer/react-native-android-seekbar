
# react-native-android-seekbar

## Getting started

`$ npm install react-native-android-seekbar --save`

### Mostly automatic installation

`$ react-native link react-native-android-seekbar

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
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
import RNAndroidSeekbar from 'react-native-android-seekbar';

// TODO: What do with the module?
RNAndroidSeekbar;
```
  